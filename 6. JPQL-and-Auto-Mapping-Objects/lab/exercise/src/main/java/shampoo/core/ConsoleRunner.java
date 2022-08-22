package shampoo.core;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import shampoo.services.interfaces.IngredientService;
import shampoo.services.interfaces.LabelService;
import shampoo.services.interfaces.ShampooService;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private IngredientService ingredientService;
    private LabelService labelService;
    private ShampooService shampooService;


    public ConsoleRunner(IngredientService ingredientService, LabelService labelService, ShampooService shampooService) {
        this.ingredientService = ingredientService;
        this.labelService = labelService;
        this.shampooService = shampooService;
    }

    @Override
    public void run(String... args) {


        this.ingredientService.updatePriceByTenPercent();



    }
}
