package com.tecacet.jflat.conversion;


public interface ConverterRegistry {

	 <TO,FROM> void registerConverter(Class<FROM> type, DataConverter<TO,FROM> converter);
	
	void deregister(Class<?> type);
	
}
