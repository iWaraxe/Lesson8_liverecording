package by.issoft.sample.domain;

import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;

public class Age {

    public static Map<Byte, Age> agesPool = new HashMap<>();
    private final byte val;

    private Age(byte val) {
        checkArgument(val >= 0, "Age must be positive");
        checkArgument(val <= 125, "Age must be less than 125");
        this.val = val;
    }

    public int intValue() {
        return val;
    }



    public static Age of(int age) {
        byte byteAge = (byte) age;
        final Age fromPool = agesPool.get(byteAge);
        if (fromPool != null) {
            return fromPool;
        }
        final Age newAge = new Age(byteAge);
        agesPool.put(byteAge, newAge);

        return newAge;
    }

}
