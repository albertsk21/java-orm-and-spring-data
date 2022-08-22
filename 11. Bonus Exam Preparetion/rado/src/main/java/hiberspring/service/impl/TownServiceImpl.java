package hiberspring.service.impl;

import com.google.gson.Gson;
import hiberspring.common.Constants;
import hiberspring.domain.dtos.json.TownJson;
import hiberspring.domain.entities.Town;
import hiberspring.filepaths.FilesPaths;
import hiberspring.repository.TownRepository;
import hiberspring.service.TownService;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
public class TownServiceImpl implements TownService {

    private TownRepository townRepository;
    private FileUtil fileUtil;
    private ModelMapper modelMapper;
    private Gson gson;
    private ValidationUtil validationUtil;

    public TownServiceImpl(TownRepository townRepository, FileUtil fileUtil, ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil) {
        this.townRepository = townRepository;
        this.fileUtil = fileUtil;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean townsAreImported() {
        int counter = (int) this.townRepository.count();
        return counter > 0;
    }

    @Override
    public String readTownsJsonFile() throws IOException {
        return this.fileUtil.readFile(FilesPaths.TOWN_JSON);
    }

    @Override
    public String importTowns(String townsFileContent) throws IOException, InstantiationException, IllegalAccessException {

        TownJson[] townsJson = this.gson.fromJson(this.readTownsJsonFile(),TownJson[].class);

        StringBuilder output = new StringBuilder();

        for (TownJson townJson : townsJson) {
            Town townDB = this.modelMapper.map(townJson,Town.class);

            if(!validationUtil.isValid(townJson)){
                output.append(Constants.INCORRECT_DATA_MESSAGE);
            }

            this.townRepository.saveAndFlush(townDB);
            output.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE,townDB.getClass().getSimpleName(),townDB.getName()));
        }
        return output.toString();
    }



}
