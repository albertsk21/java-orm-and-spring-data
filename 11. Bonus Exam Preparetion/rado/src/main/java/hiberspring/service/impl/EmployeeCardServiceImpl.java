package hiberspring.service.impl;

import com.google.gson.Gson;
import hiberspring.common.Constants;
import hiberspring.domain.dtos.json.EmployeeCardJson;
import hiberspring.domain.entities.EmployeeCard;
import hiberspring.filepaths.FilesPaths;
import hiberspring.repository.EmployeeCardRepository;
import hiberspring.service.EmployeeCardService;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
public class EmployeeCardServiceImpl implements EmployeeCardService {


    private EmployeeCardRepository employeeCardRepository;
    private Gson gson;
    private ModelMapper modelMapper;
    private FileUtil fileUtil;
    private ValidationUtil validationUtil;


    public EmployeeCardServiceImpl(EmployeeCardRepository employeeCardRepository, Gson gson, ModelMapper modelMapper, FileUtil fileUtil, ValidationUtil validationUtil) {
        this.employeeCardRepository = employeeCardRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean employeeCardsAreImported() {
        return (int) this.employeeCardRepository.count() > 0;
    }

    @Override
    public String readEmployeeCardsJsonFile() throws IOException {
        return this.fileUtil.readFile(FilesPaths.EMPLOYEE_CARDS_JSON);
    }

    @Override
    public String importEmployeeCards(String employeeCardsFileContent) throws IOException {
        EmployeeCardJson[] employeeCardsJson = this.gson.fromJson(this.readEmployeeCardsJsonFile(),EmployeeCardJson[].class);

        StringBuilder output = new StringBuilder();
        for (EmployeeCardJson employeeCardJson : employeeCardsJson) {

            try {

                this.ifExistGenerateError(employeeCardJson.getNumber());
                this.ifAFieldIsNullGenerateAError(employeeCardJson);
                EmployeeCard card = this.modelMapper.map(employeeCardJson,EmployeeCard.class);

                this.employeeCardRepository.saveAndFlush(card);
                output.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE,card.getClass().getSimpleName(),card.getNumber()));

            }catch (IllegalArgumentException | InstantiationException | IllegalAccessException e){
                output.append(Constants.INCORRECT_DATA_MESSAGE);
            }

        }
        return output.toString();
    }


   public void ifExistGenerateError(String number){

        EmployeeCard card = this.employeeCardRepository.getCardByNumber(number);

        if(card != null){
            throw new IllegalArgumentException();
        }




    }
   public void ifAFieldIsNullGenerateAError(EmployeeCardJson card) throws InstantiationException, IllegalAccessException {
        if(!this.validationUtil.isValid(card)){
            throw new IllegalArgumentException();
        }
   }

}
