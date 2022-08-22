package hiberspring.service.impl;

import com.google.gson.Gson;
import hiberspring.common.Constants;
import hiberspring.domain.dtos.json.BranchJson;
import hiberspring.domain.entities.Branch;
import hiberspring.domain.entities.Town;
import hiberspring.filepaths.FilesPaths;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.TownRepository;
import hiberspring.service.BranchService;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
public class BranchServiceImpl implements BranchService {

    private BranchRepository branchRepository;
    private TownRepository townRepository;
    private Gson gson;
    private ModelMapper modelMapper;
    private FileUtil fileUtil;
    private ValidationUtil validationUtil;
    public BranchServiceImpl(BranchRepository branchRepository, TownRepository townRepository, Gson gson, ModelMapper modelMapper, FileUtil fileUtil, ValidationUtil validationUtil) {
        this.branchRepository = branchRepository;
        this.townRepository = townRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
        this.validationUtil = validationUtil;
    }


    @Override
    public Boolean branchesAreImported() {
        int counter =(int) this.branchRepository.count();
        return counter > 0;
    }

    @Override
    public String readBranchesJsonFile() throws IOException {
        return this.fileUtil.readFile(FilesPaths.BRANCHES_JSON);
    }

    @Override
    public String importBranches(String branchesFileContent) throws IOException {

        BranchJson[] branchesJson = this.gson.fromJson(this.readBranchesJsonFile(),BranchJson[].class);


        StringBuilder output = new StringBuilder();
        for (BranchJson branchJson : branchesJson) {


            try {
                Town town = this.getTown(branchJson.getTown());

                this.branchJsonIsValid(branchJson);
                Branch branchToDB = this.modelMapper.map(branchJson,Branch.class);
                branchToDB.setTown(town);
                this.branchRepository.saveAndFlush(branchToDB);
                output.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE,Branch.class.getSimpleName(),branchToDB.getName()));

            }catch (IllegalArgumentException | InstantiationException | IllegalAccessException e){
                output.append(Constants.INCORRECT_DATA_MESSAGE);
            }
        }


        return output.toString();
    }


    public Town getTown(String name){
        Town town = this.townRepository.getTownByName(name);
        if(town == null){
            throw new IllegalArgumentException();
        }
        return  town;
    }


    public boolean branchJsonIsValid(BranchJson branch) throws InstantiationException, IllegalAccessException {
        if(!this.validationUtil.isValid(branch)){
            throw new IllegalArgumentException();
        }
        return true;
    }
}
