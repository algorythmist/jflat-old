package com.tecacet.jflat.conversion.jodd;

import static org.junit.Assert.*;

import java.util.Currency;

import org.junit.Test;

import com.tecacet.jflat.conversion.CurrencyConverter;
import com.tecacet.jflat.conversion.DataConverter;

public class DataConverterToJoddAdapterTest {

	@Test
	public void testConvert() {
		DataConverter<String, Currency> converter = new CurrencyConverter();
		DataConverterToJoddAdapter<String, Currency> adapter = new DataConverterToJoddAdapter<>(converter);
		assertEquals(Currency.getInstance("GBP"), adapter.convert("GBP"));
	}

}
