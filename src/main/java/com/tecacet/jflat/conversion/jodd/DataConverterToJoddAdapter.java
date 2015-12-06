package com.tecacet.jflat.conversion.jodd;

import com.tecacet.jflat.conversion.DataConverter;

import jodd.typeconverter.TypeConverter;

public class DataConverterToJoddAdapter<FROM, TO> implements TypeConverter<TO>{

	private final DataConverter<FROM, TO> converter;
	
	public DataConverterToJoddAdapter(DataConverter<FROM, TO> converter) {
		super();
		this.converter = converter;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TO convert(Object from) {
		return converter.convert((FROM)from);
	}

}
