package com.tecacet.jflat.conversion;


public interface ConverterRegistry {

	/**
	 * Register a converter for a type
	 * 
	 * @param type
	 * @param converter
	 */
	<FROM, TO> void registerConverter(Class<FROM> type, DataConverter<TO, FROM> converter);
	
	/**
	 * Register a converter for a specific property
	 * 
	 * @param propertyName
	 * @param converter
	 */
	<FROM, TO> void registerConverter(String propertyName, DataConverter<TO, FROM> converter);

	<FROM, TO> DataConverter<FROM, TO> getConverter(String propertyName);
	
}
