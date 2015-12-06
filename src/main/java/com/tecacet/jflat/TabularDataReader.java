package com.tecacet.jflat;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.List;

import com.tecacet.jflat.conversion.ConverterRegistry;

public interface TabularDataReader<T> {

	//TODO support readable?
	List<T> readAll(Reader reader) throws IOException;
	
	List<T> readAll(InputStream is) throws IOException;
	
	void readWithCallback(InputStream is, FlatFileReaderCallback<T> callback) throws IOException;
	
	ConverterRegistry getConverterRegistry();
}
