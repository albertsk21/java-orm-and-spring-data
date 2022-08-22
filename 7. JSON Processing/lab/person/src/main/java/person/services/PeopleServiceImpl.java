package person.services;


import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import person.dto.PersonDto;
import person.models.Person;
import person.repositories.PersonRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class PeopleServiceImpl implements PeopleService {


    private final Gson gson;
    private final ModelMapper modelMapper;
    private final PersonRepository personRepository;
    private final String PERSON_PATH = "src/main/resources/static/files/json/people.json";

    public PeopleServiceImpl(Gson gson, ModelMapper modelMapper, PersonRepository personRepository) {
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.personRepository = personRepository;
    }

    private String readInformationFromFile() throws IOException {
        return Files.readString(Path.of(this.PERSON_PATH));
    }

    @Override
    public void registerPeople() throws IOException {

        PersonDto[] people = this.gson.fromJson(this.readInformationFromFile(),PersonDto[].class);

        for (PersonDto personDto: people) {
            Person person = this.modelMapper.map(personDto,Person.class);

            this.personRepository.save(person);


        }

    }
}
