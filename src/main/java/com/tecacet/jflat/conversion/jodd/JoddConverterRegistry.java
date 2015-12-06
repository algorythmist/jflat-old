package com.tecacet.jflat.conversion.jodd;

import com.tecacet.jflat.conversion.ConverterRegistry;
import com.tecacet.jflat.conversion.DataConverter;

import jodd.typeconverter.TypeConverterManager;

public class JoddConverterRegistry implements ConverterRegistry {

	private static final JoddConverterRegistry INSTANCE = new JoddConverterRegistry();

	private JoddConverterRegistry() {
	}

	public static ConverterRegistry getInstance() {
		return INSTANCE;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public <TO, FROM> void registerConverter(Class<FROM> type, DataConverter<TO, FROM> converter) {
		TypeConverterManager.register(type, new DataConverterToJoddAdapter(converter));
	}

	@Override
	public void deregister(Class<?> type) {
		TypeConverterManager.unregister(type);
	}

}
