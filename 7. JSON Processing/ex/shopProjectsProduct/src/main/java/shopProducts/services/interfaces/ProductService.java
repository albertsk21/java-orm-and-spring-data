package shopProducts.services.interfaces;

import java.io.IOException;

public interface ProductService {
    void registerProducts() throws IOException;

    void getAllProductsWithRange() throws IOException;
}
