package com.tecacet.jflat.conversion.jodd;

import com.tecacet.jflat.conversion.DataConverter;

import jodd.typeconverter.TypeConverter;

public class DataConverterToJoddAdapter<TO> implements TypeConverter<TO>{

	private final DataConverter<Object, TO> converter;
	
	public DataConverterToJoddAdapter(DataConverter<Object, TO> converter) {
		super();
		this.converter = converter;
	}

	@Override
	public TO convert(Object from) {
		return converter.convert(from);
	}

}
