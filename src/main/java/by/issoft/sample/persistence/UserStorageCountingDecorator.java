package by.issoft.sample.persistence;

import by.issoft.sample.domain.User;
import org.checkerframework.checker.units.qual.A;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class UserStorageCountingDecorator implements UserStorage {
    private final UserStorage userStorage;
    private AtomicLong usageCounter = new AtomicLong(0);

    public UserStorageCountingDecorator(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    @Override
    public Optional<User> findByUserName(String userName) {
        usageCounter.incrementAndGet();
        return userStorage.findByUserName(userName);
    }

    @Override
    public String persist(User user) {
        usageCounter.incrementAndGet();
        return userStorage.persist(user);
    }

    @Override
    public User load(String id) {
        usageCounter.incrementAndGet();
        return userStorage.load(id);
    }

    public long getUsages() {
        return usageCounter.get();
    }
}
