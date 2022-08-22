package shopProducts.services.interfaces;

import java.io.IOException;
import java.util.List;

public interface CategoryService {
    void registerCategories() throws IOException;


    void getAllCategories() throws IOException;
}
