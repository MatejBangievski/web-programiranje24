package matejbangievski.webaud.service;

import matejbangievski.webaud.model.User;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    //login
    User login (String username, String password);
    //register
    User register (String username, String password, String repeatPassword, String name, String surname);
}
