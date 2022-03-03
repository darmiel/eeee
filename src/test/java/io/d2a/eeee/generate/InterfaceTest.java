package io.d2a.eeee.generate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import io.d2a.eeee.PromptFactory;
import io.d2a.eeee.annotation.annotations.common.Range;
import io.d2a.eeee.annotation.annotations.common.Transform;
import io.d2a.eeee.annotation.annotations.common.Transform.Type;
import io.d2a.eeee.annotation.annotations.common.Use;
import io.d2a.eeee.annotation.annotations.generate.Fill;
import io.d2a.eeee.annotation.annotations.generate.Generate;
import io.d2a.eeee.generate.random.generators.special.NameGenerator;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import org.junit.Assert;
import org.junit.Test;

public class InterfaceTest {

    private final Wizzi wiz;

    public InterfaceTest() {
        this.wiz = PromptFactory.build(Wizzi.class);
    }

    @SuppressWarnings("UnusedReturnValue")
    public interface Wizzi {
        // char

        @Generate
        char getChar();

        @Generate
        @Range({'A', 'Z'})
        char getCharRange();

        @Generate
        @Range({'Z', 'A'})
        char getCharReversedRange();

        @Generate
        @Range({'B', 'B'})
        char getCharRangeClosed();

        @Generate
        @Range({'A', 'Z', 2})
        char getCharRangeStepped();

        // int

        @Generate
        int getInt();

        @Generate
        @Range({100, 1000})
        int getIntRange();

        @Generate
        @Range({1000, 100})
        int getIntReversedRange();

        @Generate
        @Range({5, 5})
        int getIntRangeClosed();

        @Generate
        @Range({5000, 10000, 2})
        int getIntRangeStepped();

        // double

        @Generate
        double getDouble();

        @Generate
        @Range({1.0, 2.0})
        int getDoubleRange();

        @Generate
        @Range({2.0, 1.0})
        int getDoubleReversedRange();

        @Generate
        @Range({1.5, 1.5})
        double getDoubleRangeClosed();

        @Generate
        @Range({1.0, 2.0, .1})
        double getDoubleRangeSteppedEven();

        // string
        @Generate
        String getString();

        @Generate
        @Range({100, 110})
        String getStringRange();

        @Generate
        @Range({10, 1})
        String getStringRangeReversed();

        @Generate
        @Range({133, 133})
        String getStringRangeClosed();

        @Generate
        @Transform(Type.UPPER)
        String getStringUpper();

        @Generate
        @Transform(Type.LOWER)
        String getStringLower();

        @Generate
        @Use(NameGenerator.class)
        String getName();

        // arrays

        @Generate
        @Use(NameGenerator.class)
        String[] getNamesEmpty();

        @Generate
        @Use(NameGenerator.class)
        @Fill(4)
        String[] getNames();

        @Generate
        @Use(NameGenerator.class)
        @Fill(2)
        int[] getNamesInvalid();

        @Generate
        boolean getBoolean();

    }

    private <T> void x50(final Supplier<T> supplier, Consumer<T> x) {
        for (int i = 0; i < 50; i++) {
            final T t = supplier.get();
            x.accept(t);
        }
    }

    private void assertRange(final double d, final double min, final double max) {
        Assert.assertTrue(d + " should be in range " + min + " to " + max, d >= min && d <= max);
    }

    @Test
    public void testReversedRange() {
        Assert.assertThrows(RuntimeException.class, this.wiz::getCharReversedRange);
        Assert.assertThrows(RuntimeException.class, this.wiz::getDoubleReversedRange);
        Assert.assertThrows(RuntimeException.class, this.wiz::getIntReversedRange);
        Assert.assertThrows(RuntimeException.class, this.wiz::getStringRangeReversed);
    }

    @Test
    public void testChar() {
        x50(this.wiz::getChar, c -> assertRange(c, 1, 10));
        x50(this.wiz::getCharRange, c -> assertRange(c, 'A', 'Z'));
        x50(this.wiz::getCharRangeClosed, c -> assertEquals("B", String.valueOf(c)));
        x50(this.wiz::getCharRangeStepped, c -> {
            assertRange(c, 'A', 'Z');
            assertEquals(0, ('A' + c) % 2);
        });
    }

    @Test
    public void testInt() {
        x50(this.wiz::getInt, i -> assertRange(i, 1, 10));
        x50(this.wiz::getIntRange, i -> assertRange(i, 100, 1000));
        x50(this.wiz::getIntRangeClosed, i -> assertEquals(5, i.intValue()));
        x50(this.wiz::getIntRangeStepped, i -> {
            assertRange(i, 5000,10000);
            assertEquals(0, (5000 + i) % 2);
        });
    }


    @Test
    public void testString() {
        x50(this.wiz::getString, s -> assertRange(s.length(), 1, 10));
        x50(this.wiz::getStringRange, s -> assertRange(s.length(), 100, 110));
        x50(this.wiz::getStringRangeClosed, s -> assertEquals(133, s.length()));
        x50(this.wiz::getStringUpper, s -> {
            for (final char c : s.toCharArray()) {
                assertEquals(Character.toUpperCase(c), c);
            }
        });
        x50(this.wiz::getStringLower, s -> {
            for (final char c : s.toCharArray()) {
                assertEquals(Character.toLowerCase(c), c);
            }
        });

        final List<String> names = Arrays.asList(NameGenerator.NAMES);
        x50(this.wiz::getName, s -> assertTrue(names.contains(s)));
    }

    @Test
    public void testArrays() {
        final List<String> names = Arrays.asList(NameGenerator.NAMES);
        x50(this.wiz::getNamesEmpty, a -> assertEquals(0, a.length));
        x50(this.wiz::getNames, a -> {
            assertEquals(4, a.length);
            for (final String s : a) {
                assertTrue(names.contains(s));
            }
        });
    }

    @Test(expected = IllegalArgumentException.class)
    public void testArraysInvalid() {
        this.wiz.getNamesInvalid(); // type of array is not type of generator; int <-> String
    }


    @Test
    public void testBool() {
        x50(this.wiz::getBoolean, b -> {}); // just don't expect exceptions
    }

    // TODO: Double Tests

}
