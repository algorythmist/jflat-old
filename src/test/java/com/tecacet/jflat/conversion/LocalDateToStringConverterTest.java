package com.tecacet.jflat.conversion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.joda.time.LocalDate;
import org.junit.Test;

public class LocalDateToStringConverterTest {

    @Test
    public void testConvertToString() {
        LocalDateToStringConverter converter = new LocalDateToStringConverter("MM/dd/yyyy");
        LocalDate localDate = new LocalDate(1999, 2, 13);
        String result = converter.convert(localDate);
        assertEquals("02/13/1999", result);
    }
    
    @Test
    public void testDefault() {
        LocalDateToStringConverter converter = new LocalDateToStringConverter();
        LocalDate localDate = new LocalDate(1999, 2, 13);
        String result = converter.convert(localDate);
        assertEquals("13-02-1999", result);
        assertNull(converter.convert(null));
    }

}
