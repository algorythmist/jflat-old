package com.tecacet.jflat.conversion;

import static org.junit.Assert.*;

import java.util.Currency;

import org.junit.Test;

public class CurrencyConverterTest {

	@Test
	public void testConvert() {
		CurrencyConverter converter = new CurrencyConverter();
		assertEquals(Currency.getInstance("USD"), converter.convert("USD"));
		assertNull(converter.convert(null));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInvalidCurrency() {
		CurrencyConverter converter = new CurrencyConverter();
		converter.convert("HHD");
	}

}
