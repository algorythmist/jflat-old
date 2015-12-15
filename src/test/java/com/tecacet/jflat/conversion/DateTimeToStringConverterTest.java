package com.tecacet.jflat.conversion;

import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.junit.Test;

public class DateTimeToStringConverterTest {

	@Test
	public void testConvertDefault() {
		DateTimeToStringConverter converter = new DateTimeToStringConverter();
		DateTime dateTime= new DateTime(2015, 10, 10, 5, 39, 34);
		String string = converter.convert(dateTime);
		assertEquals("05:39:34", string);
	}
	
	@Test
	public void testConvertCustom() {
		DateTimeToStringConverter converter = new DateTimeToStringConverter("yy/MM/dd hh:mm");
		DateTime dateTime= new DateTime(2015, 10, 10, 5, 39, 34);
		String string = converter.convert(dateTime);
		assertEquals("15/10/10 05:39", string);
	}

}
