package com.tecacet.jflat.introspection;

import java.util.Map;

/**
 * Default property accessor for a map
 * 
 * @author Dimitri Papaioannou
 *
 */
public class MapPropertyAccessor implements PropertyAccessor<Map<String, Object>> {

	@Override
	public Object getProperty(Map<String, Object> bean, String propertyName) {
		return bean.get(propertyName);
	}

	@Override
	public void setProperty(Map<String, Object> bean, String propertyName, Object value) {
		bean.put(propertyName, value);
	}

}
