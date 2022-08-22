package softuni.exam.instagraphlite.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.entity.Picture;
import softuni.exam.instagraphlite.models.entity.User;
import softuni.exam.instagraphlite.models.json.UserJson;
import softuni.exam.instagraphlite.output.GenerateErrors;
import softuni.exam.instagraphlite.output.Output;
import softuni.exam.instagraphlite.paths.PathsInput;
import softuni.exam.instagraphlite.repository.PictureRepository;
import softuni.exam.instagraphlite.repository.PostRepository;
import softuni.exam.instagraphlite.repository.UserRepository;
import softuni.exam.instagraphlite.service.UserService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;
    private Gson gson;
    private PictureRepository pictureRepository;
    private ModelMapper modelMapper;
    private PostRepository postRepository;

    public UserServiceImpl(UserRepository userRepository, Gson gson, PictureRepository pictureRepository, ModelMapper modelMapper, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.gson = gson;
        this.pictureRepository = pictureRepository;
        this.modelMapper = modelMapper;
        this.postRepository = postRepository;
    }

    @Override
    public boolean areImported() {
        int counter = (int) this.userRepository.count();
        return counter > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return Files.readString(Path.of(PathsInput.USERS_JSON));
    }

    @Override
    public String importUsers() throws IOException {


        UserJson[] usersJson = this.gson.fromJson(this.readFromFileContent(), UserJson[].class);

        StringBuilder output = new StringBuilder("");

        for (UserJson userJson : usersJson) {
            try {
                this.isValidUser(userJson);
                Picture picture = this.getPictureJson(userJson.getProfilePicture());
                User userToDB = this.modelMapper.map(userJson, User.class);
                userToDB.setProfilePictures(picture);
                this.userRepository.saveAndFlush(userToDB);
                output.append(String.format(Output.SUCCESSFULLY_USERS,userToDB.getUsername()));
            } catch (IllegalArgumentException e) {
                output.append(String.format("%s%n", e.getMessage()));
            }
        }
        return output.toString();
    }
    private User getUserByUsername(String username){
        return this.userRepository.getUserByUsername(username);
    }
    private void generateErrorIfExistUsername(String username){
        User user = this.getUserByUsername(username);
        if(user != null){
            throw new IllegalArgumentException(GenerateErrors.INVALID_USER);
        }
    }
    private void isValidUser(UserJson user){
        this.generateErrorIfExistUsername(user.getUsername());
        this.generateErrorIfNotExistPicture(user.getProfilePicture());
        if(user.getUsername() == null || user.getPassword() == null ){
            throw new IllegalArgumentException(GenerateErrors.INVALID_USER);
        }
        if(!(user.getUsername().length() >= 2 & user.getUsername().length() <= 18) || !(user.getPassword().length() >= 4) ){
            throw new IllegalArgumentException(GenerateErrors.INVALID_USER);
        }
    }

    private void generateErrorIfNotExistPicture(String path){
        Picture picture = this.getPictureJson(path);
        if( picture == null ){
            throw new IllegalArgumentException(GenerateErrors.INVALID_USER);
        }
    }
    private Picture getPictureJson(String path){

        return this.pictureRepository.getPictureByUrl(path);
    }
    @Override
    public String exportUsersWithTheirPosts() {


        User[] users = this.userRepository.getAllUsers();
        System.out.println();

        return null;
    }
}
