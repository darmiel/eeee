package io.d2a.eeee.wrapper;

import io.d2a.eeee.wrapper.wrappers.BooleanWrapper;
import io.d2a.eeee.wrapper.wrappers.DoubleWrapper;
import io.d2a.eeee.wrapper.wrappers.IntegerWrapper;
import io.d2a.eeee.wrapper.wrappers.ScannerWrapper;
import io.d2a.eeee.wrapper.wrappers.ShortWrapper;
import io.d2a.eeee.wrapper.wrappers.StringWrapper;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Wrappers {

    public static final Map<Class<?>, Wrapper<?>> WRAPPERS = new HashMap<Class<?>, Wrapper<?>>() {{
        put(String.class, new StringWrapper());
        put(Scanner.class, new ScannerWrapper());
    }};

    static {
        final IntegerWrapper integerWrapper = new IntegerWrapper();
        WRAPPERS.put(int.class, integerWrapper);
        WRAPPERS.put(Integer.class, integerWrapper);

        final DoubleWrapper doubleWrapper = new DoubleWrapper();
        WRAPPERS.put(double.class, doubleWrapper);
        WRAPPERS.put(Double.class, doubleWrapper);

        final ShortWrapper shortWrapper = new ShortWrapper();
        WRAPPERS.put(short.class, shortWrapper);
        WRAPPERS.put(Short.class, shortWrapper);

        final BooleanWrapper booleanWrapper = new BooleanWrapper();
        WRAPPERS.put(boolean.class, booleanWrapper);
        WRAPPERS.put(Boolean.class, booleanWrapper);
    }

}
