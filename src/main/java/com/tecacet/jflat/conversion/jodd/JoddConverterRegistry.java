package com.tecacet.jflat.conversion.jodd;

import java.util.HashMap;
import java.util.Map;

import com.tecacet.jflat.conversion.ConverterRegistry;
import com.tecacet.jflat.conversion.DataConverter;

import jodd.typeconverter.TypeConverterManager;

public class JoddConverterRegistry implements ConverterRegistry {

	private Map<String, DataConverter> byProperty = new HashMap<>();

	@Override
	public <FROM, TO> void registerConverter(Class<FROM> type, DataConverter<TO, FROM> converter) {
		TypeConverterManager.register(type, new DataConverterToJoddAdapter<>(converter));
	}

	@Override
	public <FROM, TO> void registerConverter(String propertyName, DataConverter<TO, FROM> converter) {
		byProperty.put(propertyName, converter);
	}

	@Override
	public <FROM, TO> DataConverter<FROM, TO> getConverter(String propertyName) {
		return byProperty.get(propertyName);
	}

}
