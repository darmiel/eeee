package io.d2a.eeee.converter;

import junit.framework.TestCase;

public class StringConverterTest extends TestCase {

    public void testToPowString() {
        assertEquals("¹²³", StringConverter.toPowString(123));
        assertEquals("⁰", StringConverter.toPowString(0));
        assertEquals("⁻¹²³", StringConverter.toPowString(-123));
    }
}