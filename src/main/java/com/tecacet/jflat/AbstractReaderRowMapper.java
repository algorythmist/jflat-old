package com.tecacet.jflat;
import com.tecacet.jflat.conversion.ConverterRegistry;
import com.tecacet.jflat.conversion.jodd.JoddConverterRegistry;

public abstract class AbstractReaderRowMapper<T> implements ReaderRowMapper<T> {

	private final ConverterRegistry converterRegistry = new JoddConverterRegistry();

	public ConverterRegistry getConverterRegistry() {
		return converterRegistry;
	}

}
