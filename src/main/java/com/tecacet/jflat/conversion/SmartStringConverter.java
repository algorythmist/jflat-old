package com.tecacet.jflat.conversion;

import java.util.HashMap;
import java.util.Map;

/**
 * This class allows arbitrary conversion of data types to strings by
 * registering ToStringConverters.
 * 
 */
public class SmartStringConverter implements ToStringConverter<Object> {

	private Map<Class<?>, ToStringConverter<?>> registry = new HashMap<Class<?>, ToStringConverter<?>>();

	/**
	 * Register a ToString converter for a class. The converter will apply to
	 * all subtypes and implementations
	 * 
	 * @param type
	 *            the supertype for which this converter is applicable
	 * @param converter
	 */
	public void registerConverter(Class<?> type, ToStringConverter<?> converter) {
		registry.put(type, converter);
	}

	public void deregisterConverter(Class<?> type) {
		registry.remove(type);
	}

	public boolean supports(Class<?> type) {
		return registry.containsKey(type);
	}
	
    @Override
	public String convert(Object from) {
		if (from == null) {
			return null;
		}
		Class<?> type = from.getClass();

		for (Class<?> clazz : registry.keySet()) {
			if (clazz.isAssignableFrom(type)) {
				ToStringConverter converter = registry.get(clazz);
				return converter.convert(from);
			}
		}
		return from.toString();
	}

}
