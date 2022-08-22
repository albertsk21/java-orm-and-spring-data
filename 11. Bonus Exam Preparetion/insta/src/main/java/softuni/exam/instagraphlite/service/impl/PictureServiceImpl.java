package softuni.exam.instagraphlite.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.entity.Picture;
import softuni.exam.instagraphlite.models.json.PictureJson;
import softuni.exam.instagraphlite.output.GenerateErrors;
import softuni.exam.instagraphlite.output.Output;
import softuni.exam.instagraphlite.paths.PathsInput;
import softuni.exam.instagraphlite.repository.PictureRepository;
import softuni.exam.instagraphlite.service.PictureService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class PictureServiceImpl implements PictureService {

    private Gson gson;
    private ModelMapper modelMapper;
    private PictureRepository pictureRepository;

    public PictureServiceImpl(Gson gson, ModelMapper modelMapper, PictureRepository pictureRepository) {
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.pictureRepository = pictureRepository;
    }

    @Override
    public boolean areImported() {

        int counter = (int) this.pictureRepository.count();
        return counter != 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return Files.readString(Path.of(PathsInput.PICTURES_JSON));
    }

    @Override
    public String importPictures() throws IOException {
        PictureJson[] picturesJson = this.gson.fromJson(this.readFromFileContent(),PictureJson[].class);

        StringBuilder output = new StringBuilder("");
        for (int i = 0; i < picturesJson.length ; i++) {
            try{
                PictureJson currentPictureJson =  picturesJson[i];
                this.checkPictureJson(currentPictureJson);
                Picture pictureToDB = this.modelMapper.map(currentPictureJson,Picture.class);
                this.pictureRepository.saveAndFlush(pictureToDB);
                output.append(String.format(Output.SUCCESSFULLY_PICTURE,pictureToDB.getSize().doubleValue()));
            }catch (IllegalArgumentException e){
                output.append(String.format("%s%n",e.getMessage()));
            }
        }
        return output.toString();
    }



    @Override
    public String exportPictures() {

        Picture[] pictures = this.pictureRepository.getAllPicture();
        StringBuilder output = new StringBuilder();
        for (Picture picture : pictures) {
            output.append(String.format("%.2f - %s%n", picture.getSize().doubleValue(), picture.getPath()));
        }
        return output.toString();
    }

    private void checkPictureJson(PictureJson pictureJson){



        Picture picture = this.pictureRepository.getPictureByUrl(pictureJson.getPath());

        if(pictureJson.getPath() == null){
            throw new IllegalArgumentException(GenerateErrors.INVALID_PICTURE);
        }
        if(pictureJson.getSize() == null || picture != null ){
            throw new IllegalArgumentException(GenerateErrors.INVALID_PICTURE);
        }
        if(!(pictureJson.getSize().doubleValue() >= 500 && pictureJson.getSize().doubleValue() <= 60000 ) ){
            throw new IllegalArgumentException(GenerateErrors.INVALID_PICTURE);
        }
    }

}
