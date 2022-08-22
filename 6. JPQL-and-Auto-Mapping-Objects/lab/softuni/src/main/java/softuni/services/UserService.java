package softuni.services;

import java.io.IOException;

public interface UserService {


    boolean findUserWithUsername(String username);

    void writeCommand() throws IOException;
}
