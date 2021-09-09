package by.issoft.sample.service;

import by.issoft.sample.domain.User;
import by.issoft.sample.persistence.UserStorage;

import java.util.Optional;

import static by.issoft.sample.domain.UserStatus.ACTIVE;
import static com.google.common.base.Preconditions.checkState;

public class UserServiceImpl implements UserService {

    private final UserStorage userStorage;
    private final UserValidator userValidator;

    public UserServiceImpl(UserStorage userStorage, UserValidator userValidator) {
        this.userStorage = userStorage;
        this.userValidator = userValidator;
    }
    /**
     *
     * @param user user to create
     * @return id of created user
     */
    @Override
    public String createUser(User user) {
        final boolean valid = userValidator.validateForCreation(user);
        if (!valid) {
            throw new IllegalArgumentException("user is not valid" + user);
        }

        Optional<User> byUserName = userStorage.findByUserName(user.getUsername());
        checkState(byUserName.isEmpty(), "username is busy");

        user.setStatus(ACTIVE);
        final String id = userStorage.persist(user);
        user.setId(id);

        return id;
    }

    @Override
    public User findById(String id) {
        return userStorage.load(id);
    }

}
