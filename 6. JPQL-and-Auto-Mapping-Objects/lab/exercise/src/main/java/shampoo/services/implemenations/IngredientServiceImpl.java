package shampoo.services.implemenations;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import shampoo.entities.database.Ingredient;
import shampoo.entities.dto.IngredientDto;
import shampoo.repository.IngredientRepository;
import shampoo.services.interfaces.IngredientService;

import java.util.ArrayList;
import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {
    @Autowired
    private IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public void saveIngredient(Ingredient ingredient) {
        this.ingredientRepository.save(ingredient);
    }

    @Override
    public void findAllIngredientsStartWith(String letter) {
        for ( IngredientDto ingredientDto: this.IngredientsDtoList()) {
            if(ingredientDto.getName().startsWith(letter)){
                System.out.println(ingredientDto.getName());
            }
        }
    }

    @Override
    public void deleteIngredientByGiveAName(String name) {
        this.ingredientRepository.deleteIngredientByName(name);
    }

    @Override
    public void delete(String name) {

        for (Ingredient ingredient : this.ingredientRepository.findAll()) {
            if(ingredient.getName().equals(name)){
                this.ingredientRepository.deleteIngredient(ingredient);
                break;
            }
        }

    }

    @Override
    public void updatePriceByTenPercent() {


        this.ingredientRepository.increasePriceByTenPercent();
    }

    public List<IngredientDto> IngredientsDtoList(){

        ModelMapper modelMapper =  new ModelMapper();

        List<IngredientDto> ingredientDtoList = new ArrayList<>();


       for (  Ingredient ingredient: this.ingredientRepository.findAll()) {

           IngredientDto ingredientDto =  modelMapper.map(ingredient,IngredientDto.class);
           ingredientDtoList.add(ingredientDto);
       }


       return ingredientDtoList;
    }
}
