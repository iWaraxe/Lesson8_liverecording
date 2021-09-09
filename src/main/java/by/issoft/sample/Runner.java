package by.issoft.sample;

import by.issoft.sample.domain.*;
import by.issoft.sample.persistence.InMemoryUserStorage;
import by.issoft.sample.persistence.UserStorage;
import by.issoft.sample.persistence.UserStorageCountingDecorator;
import by.issoft.sample.persistence.UserTimeLimitedCache;
import by.issoft.sample.service.UserService;
import by.issoft.sample.service.UserServiceImpl;
import by.issoft.sample.service.UserValidator;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Runner {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InterruptedException {

        UserValidator userValidator = new UserValidator();
        UserStorage userStorage = new InMemoryUserStorage();

        UserStorageCountingDecorator counter = new UserStorageCountingDecorator(userStorage);

        UserStorage cache = new UserTimeLimitedCache(100, counter);

        //decorator, proxy, chain of responsibilities
        UserService userService = new UserServiceImpl(cache ,userValidator);

        final String id = userService.createUser(new User("vpupkin", "vasya", "pupkin"));

        System.out.println(userService.findById(id));
        System.out.println(userService.findById(id));
        System.out.println(userService.findById(id));
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println(userService.findById(id));
        System.out.println(userService.findById(id));

        System.out.println(counter.getUsages());
   }
}

class A {
    private final M1Provider m1Provider;

    protected A(M1Provider m1Provider) {
        this.m1Provider = m1Provider;
    }

    public void businessLogic() {
        //
        //
        //
        int i = m1Provider.m1();
        //
        //use i
    }

}

interface M1Provider {
    int I = 1;
    int m1();
}

class B implements M1Provider {
    @Override
    public int m1() {
        return 42;
    }
}

class C implements M1Provider {

    @Override
    public int m1() {
        return 41;
    }
}












