package matejbangievski.webaud.resource;

import matejbangievski.webaud.bootstrap.DataHolder;
import matejbangievski.webaud.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public class InMemoryUserRepository {
    //findByUsername
    public Optional<User> findByUsername (String username) {
        return DataHolder.users.stream().filter(user -> user.getUsername().equals(username)).findFirst();
    }
    //findByUsernameAndPassword
    public Optional<User> findByUsernameAndPassword(String username, String password) {
        return DataHolder.users.stream().filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password)).findFirst();
    }
    //saveOrUpdate
    public User saveOrUpdate(User user) {
        DataHolder.users.removeIf(u -> u.getUsername().equals(user.getUsername()));
        DataHolder.users.add(user);
        return user;
    }
    //deleteByUsername
    public void deleteByUsername(String username) {
        DataHolder.users.removeIf(u -> u.getUsername().equals(username));
    }
}
