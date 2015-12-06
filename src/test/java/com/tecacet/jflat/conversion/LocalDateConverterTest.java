package com.tecacet.jflat.conversion;

import static org.junit.Assert.*;

import org.joda.time.LocalDate;
import org.junit.Test;

import com.tecacet.jflat.conversion.LocalDateConverter;


public class LocalDateConverterTest {

    

    @Test
    public void testConvertToTypeClassObject() {
    	LocalDateConverter converter = new LocalDateConverter("MM/dd/yyyy");
        LocalDate result = (LocalDate) converter.convert("02/13/1999");
        LocalDate localDate = new LocalDate(1999, 2, 13);
        assertEquals(localDate, result);
    }

    @Test
    public void testConvertWithMutlipleFormats() {
        LocalDate referenceDate = new LocalDate(1999, 2, 13);
    	String[] dateFormats = {"MM/dd/yyyy", "MM-dd-yyyy"};
    	LocalDateConverter multiFormatConverter = new LocalDateConverter(dateFormats);
    	
        LocalDate dateWithSlashes = (LocalDate) multiFormatConverter.convert("02/13/1999");
        assertEquals("Converted date from MM/dd/yyyy format", referenceDate, dateWithSlashes);
        
        LocalDate dateWithHyphens = (LocalDate) multiFormatConverter.convert("02-13-1999");
        assertEquals("Converted date from MM-dd-yyyy format", referenceDate, dateWithHyphens);
    }
    
    @Test
    public void testConvertWrongFormat() {
    	LocalDateConverter converter = new LocalDateConverter("MM/dd/yyyy");
    	try {
    		converter.convert("02-13-1999");
            fail();
        } catch (Exception ce) {
            assertEquals("Error converting 02-13-1999 to LocalDate", ce.getMessage());
        }
    }
    
    @Test
    public void testConvertNull() {
    	LocalDateConverter converter = new LocalDateConverter("MM/dd/yyyy");
        LocalDate result = (LocalDate) converter.convert(null);
        assertEquals(null, result);
    }


}
