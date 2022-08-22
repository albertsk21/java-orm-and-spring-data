package shampoo.services.interfaces;


import shampoo.entities.database.Ingredient;

import java.util.List;

public interface IngredientService {

    void saveIngredient(Ingredient ingredient);
    void findAllIngredientsStartWith(String letter);
    void deleteIngredientByGiveAName(String name);
    void delete(String name);
    void updatePriceByTenPercent();
}
