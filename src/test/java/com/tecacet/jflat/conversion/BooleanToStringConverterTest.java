package com.tecacet.jflat.conversion;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.tecacet.jflat.conversion.BooleanToStringConverter;

public class BooleanToStringConverterTest {

    @Test
    public void testConvertToString() {
        BooleanToStringConverter converter = new BooleanToStringConverter();
        assertEquals("N", converter.convert(false));
        assertEquals("Y", converter.convert(true));
        
        converter.setNoString("No");
        converter.setYesString("Si");
        assertEquals("No", converter.convert(false));
        assertEquals("Si", converter.convert(true));
    }
    
    @Test
    public void testCustomConvert() {
        BooleanToStringConverter converter = new BooleanToStringConverter("SI","NO");
        assertEquals("SI", converter.getYesString());
        assertEquals("NO", converter.getNoString());
        assertEquals("NO", converter.convert(false));
        assertEquals("SI", converter.convert(true));

    }

}
