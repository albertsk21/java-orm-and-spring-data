package shampoo.services.implemenations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shampoo.entities.database.Shampoo;
import shampoo.entities.dto.ShampooDto;
import shampoo.repository.ShampooRepository;
import shampoo.services.interfaces.ShampooService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Service
public class ShampooServiceImpl implements ShampooService {

    @Autowired
    private ShampooRepository shampooRepository;


    @Override
    public void saveShampoo(Shampoo shampoo) {
        this.shampooRepository.save(shampoo);
    }

    @Override
    public Shampoo findShampooById(Long id) {
        return this.shampooRepository.findShampooById(id);
    }

    @Override
    public void findBySize(String size) {
        try {
            int numberOfSize = convertSize(size);
            List<Shampoo> shampoos = this.shampooRepository.findBySize(numberOfSize);
              printShampoo(shampoos);

        }catch (IllegalArgumentException exception){
            System.out.println(exception.getMessage());
        }



    }

    @Override
    public void findBySizeAndLabelId(String size, Long labelId) {
        try {
            int numberOfSize = convertSize(size);
            List<Shampoo> shampoos = this.shampooRepository.findBySizeAndLabel(numberOfSize,labelId);
            printShampoo(shampoos);

        }catch (IllegalArgumentException exception){
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public void findByPrice(BigDecimal price) {
        printShampoo(this.shampooRepository.findShampooHigherThan(price));
    }

    @Override
    public int countAllShampoosByPrice(BigDecimal price) {
        return this.shampooRepository.countShampoosLessThanGivenPrice(price);
    }

    @Override
    public void findAllShampoosByIngredients(String[] ingredientsNames ) {


        for (String brand : this.shampooRepository.findShampoosByIngredientName(ingredientsNames[0],ingredientsNames[1])) {
            System.out.println(brand);
        }



    }

    @Override
    public void findShampoosHaveNumberOfIngredients(int number) {


        for (ShampooDto shampooDto : shampooDtoList() ) {


            if(shampooDto.getIngredients().size() < number ){


                System.out.println(shampooDto.getBrand());
            }
        }


    }

    public List<ShampooDto> shampooDtoList(){
        List<ShampooDto> shampoosDTO = new ArrayList<>();

        ModelMapper modelMapper =  new ModelMapper();
        for (Shampoo shampoo: this.shampooRepository.findAll()) {
            ShampooDto shampooDto =  modelMapper.map(shampoo,ShampooDto.class);
            shampoosDTO.add(shampooDto);
        }
        return shampoosDTO;

    }
    public void printShampoo(List<Shampoo> shampoos){
        for (ShampooDto shampooDto : shampooDtoList()) {
            System.out.println(shampooDto.toString());
        }
    }
    public int convertSize(String size){
        return switch (size) {
            case "SMALLER" -> 0;
            case "MEDIUM" -> 1;
            case "HIGHER" -> 2;
            default -> {throw new IllegalArgumentException("size is invalid1"); }
        };

    }

}
