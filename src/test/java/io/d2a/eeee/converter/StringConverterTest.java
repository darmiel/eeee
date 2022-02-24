package io.d2a.eeee.converter;

import junit.framework.TestCase;

public class StringConverterTest extends TestCase {

    public void testToPowString() {
        assertEquals("¹²³", StringConverter.toPowString(123));
        assertEquals("", StringConverter.toPowString(0));
        assertEquals("", StringConverter.toPowString(-1));
    }
}