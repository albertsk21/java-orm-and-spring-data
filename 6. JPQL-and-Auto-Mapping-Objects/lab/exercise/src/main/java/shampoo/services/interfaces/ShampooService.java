package shampoo.services.interfaces;


import shampoo.entities.database.Shampoo;
import java.math.BigDecimal;


public interface ShampooService {


    void saveShampoo(Shampoo shampoo);
    Shampoo findShampooById(Long id);
    void findBySize(String size);
    void findBySizeAndLabelId(String size, Long labelId);
    void findByPrice(BigDecimal price);
    int countAllShampoosByPrice(BigDecimal price);
    void findAllShampoosByIngredients(String[] ingredients );

    void findShampoosHaveNumberOfIngredients(int number);
}
