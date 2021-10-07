package by.issoft.sample.persistence;

import by.issoft.sample.domain.Age;
import by.issoft.sample.domain.User;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserStorageTest {

    private UserStorage userStorage = new InMemoryUserStorage();

    @Test
    public void testPersist() {
        //given
        User user = anyValidUser();

        //when
        final String id = userStorage.persist(user);

        //then
        assertThat(id, is(notNullValue())); // Hamcrest
        final User loaded = userStorage.load(id);
        assertThat(loaded, is(equalTo(user))); // Hamcrest
    }

    @Test
    public void testPersist_null() {
        //when
        assertThrows(NullPointerException.class, () -> userStorage.persist(null));
    }

    User anyValidUser() {
        User user = new User("vpupkin");
        user.setAge(Age.of(23));
        user.setFirstName("Vasya");
        user.setLastName("Pupkin");

        return user;
    }
}