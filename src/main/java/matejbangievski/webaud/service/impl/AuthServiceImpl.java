package matejbangievski.webaud.service.impl;

import matejbangievski.webaud.model.User;
import matejbangievski.webaud.model.exceptions.InvalidArgumentsException;
import matejbangievski.webaud.model.exceptions.InvalidUserCredentialsException;
import matejbangievski.webaud.model.exceptions.PasswordsDoNotMatchException;
import matejbangievski.webaud.resource.InMemoryUserRepository;
import matejbangievski.webaud.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final InMemoryUserRepository inMemoryUserRepository;

    public AuthServiceImpl(InMemoryUserRepository inMemoryUserRepository) {
        this.inMemoryUserRepository = inMemoryUserRepository;
    }

    @Override
    public User login(String username, String password) {
        //Check if username and password are not null or empty
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }

        return inMemoryUserRepository.findByUsernameAndPassword(username, password).orElseThrow(InvalidUserCredentialsException::new);
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname) {
        //Check if username, password, repeatpassword, name and surname are not null or empty
        if (username == null || password == null || repeatPassword == null || name == null || surname == null || username.isEmpty() || password.isEmpty() ||
                repeatPassword.isEmpty() || name.isEmpty() || surname.isEmpty()) {
            throw new InvalidArgumentsException();
        }

        //Check if the passwords match
        if (!password.equals(repeatPassword)) {
            throw new PasswordsDoNotMatchException();
        }

        return inMemoryUserRepository.saveOrUpdate(new User(username, password, name, surname));
    }
}