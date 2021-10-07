package by.issoft.sample.persistence;

import by.issoft.sample.domain.User;

import java.io.FileNotFoundException;
import java.util.Optional;

public interface UserStorage {
    Optional<User> findByUserName(String userName);

    String persist(User user);

    User load(String id);

}

