package io.d2a.eeee.converter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StringConverterTest {

    private static class TestCase {

        private final String expected;
        private final int value;

        public TestCase(final String expected, final int value) {
            this.expected = expected;
            this.value = value;
        }
    }

    @Test
    public void testToPowString() {
        final TestCase[] cases = {
            new TestCase("¹²³", 123),
            new TestCase("⁻¹²³", -123),
            new TestCase("¹", 1),
            new TestCase("⁻¹", -1),
            new TestCase("⁰", 0),
            new TestCase("⁰", -0),
        };

        for (final TestCase testCase : cases) {
            assertEquals(testCase.expected, StringConverter.toPowString(testCase.value));
        }
    }

    @Test
    public void testRepeat() {
        final TestCase[] cases = {
            new TestCase("----------------------------------------------------------------", 64),
            new TestCase("---", 3),
            new TestCase("", 0),
            new TestCase("-", 1)
        };

        for (final TestCase testCase : cases) {
            assertEquals(testCase.expected, StringConverter.repeat("-", testCase.value));
        }
    }

}