package shopProducts.services.interfaces;

import java.io.IOException;

public interface UserService {
    void registerUsers() throws IOException;

    void getAllUserAtLeast1Sold() throws IOException;
}
