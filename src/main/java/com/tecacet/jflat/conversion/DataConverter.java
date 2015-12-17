package com.tecacet.jflat.conversion;

public interface DataConverter<FROM, TO> {

	TO convert(FROM from);
}
