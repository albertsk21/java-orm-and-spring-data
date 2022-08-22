package softuni.exam.instagraphlite.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.config.interfaces.XmlParser;
import softuni.exam.instagraphlite.models.entity.Picture;
import softuni.exam.instagraphlite.models.entity.Post;
import softuni.exam.instagraphlite.models.entity.User;
import softuni.exam.instagraphlite.models.xml.PostsXml;
import softuni.exam.instagraphlite.output.GenerateErrors;
import softuni.exam.instagraphlite.output.Output;
import softuni.exam.instagraphlite.paths.PathsInput;
import softuni.exam.instagraphlite.repository.PictureRepository;
import softuni.exam.instagraphlite.repository.PostRepository;
import softuni.exam.instagraphlite.repository.UserRepository;
import softuni.exam.instagraphlite.service.PostService;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private XmlParser xmlParser;
    private UserRepository userRepository;
    private PictureRepository pictureRepository;
    private ModelMapper modelMapper;

    public PostServiceImpl(PostRepository postRepository, XmlParser xmlParser, UserRepository userRepository, PictureRepository pictureRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.xmlParser = xmlParser;
        this.userRepository = userRepository;
        this.pictureRepository = pictureRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        int counter = (int) this.postRepository.count();
        return counter > 0;
    }
    @Override
    public String readFromFileContent() throws IOException {
        return Files.readString(Path.of(PathsInput.POSTS_XML));
    }
    @Override
    public String importPosts() throws IOException, JAXBException {

        PostsXml postsXml = this.xmlParser.export(PostsXml.class,PathsInput.POSTS_XML);
        StringBuilder output = new StringBuilder();
        for (int i = 0; i <postsXml.getPosts().size() ; i++) {
            try {

                this.checkCaption(postsXml.getPosts().get(i).getCaption());
                Picture picture = this.getPicture(postsXml.getPosts().get(i).getPicture().getPath());
                User user = this.getUserByUsername(postsXml.getPosts().get(i).getUser().getUsername());
                Post postToDB = this.modelMapper.map(postsXml.getPosts().get(i),Post.class);
                postToDB.setUser(user);
                postToDB.setPicture(picture);
                this.postRepository.saveAndFlush(postToDB);
                output.append(String.format(Output.SUCCESSFULLY_POSTS,user.getUsername()));
            }catch (IllegalArgumentException e){
                output.append(String.format("%s%n",GenerateErrors.INVALID_POST));
            }
        }


        return output.toString();
    }



    private void checkCaption( String caption){

        if(!(caption.length() >= 21)){
            throw new IllegalArgumentException();
        }

    }
    private Picture getPicture(String url){
        Picture picture = this.pictureRepository.getPictureByUrl(url);
        if(picture == null){
            throw new IllegalArgumentException();
        }
        return  picture;
    }
    private User getUserByUsername(String username){

        User user = this.userRepository.getUserByUsername(username);
        if(user == null){
            throw new IllegalArgumentException();
        }
        return  user;
    }
}
