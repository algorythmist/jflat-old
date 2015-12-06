package com.tecacet.jflat;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.tecacet.jflat.conversion.ConverterRegistry;

public interface TabularDataReader<T> {
	
	List<T> readAll(InputStream is) throws IOException;
	
	void readWithCallback(InputStream is, TabularDataReaderCallback<T> callback) throws IOException;
	
	ConverterRegistry getConverterRegistry();
}
