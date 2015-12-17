package com.tecacet.jflat.conversion;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

import com.tecacet.jflat.conversion.DateToStringConverter;
import com.tecacet.jflat.conversion.DoubleToStringConverter;
import com.tecacet.jflat.conversion.SmartStringConverter;

public class SmartStringConverterTest {

	@Test
	public void testConverter() {
		SmartStringConverter converter = new SmartStringConverter();
		converter.registerConverter(Date.class, new DateToStringConverter());
		converter.registerConverter(Double.class, new DoubleToStringConverter());
		Calendar c = new GregorianCalendar(2010, 2, 3);
		assertEquals("03-03-2010", converter.convert(c.getTime()));
		assertTrue(converter.supports(Date.class));
		//TODO must support subclasses
		//assertTrue(converter.supports(java.sql.Date.class));
		converter.deregisterConverter(Date.class);
		assertFalse(converter.supports(Date.class));
	}

}
