package com.tecacet.jflat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import com.tecacet.jflat.conversion.ConverterRegistry;

public abstract class AbstractReader<T> implements TabularDataReader<T> {

	protected int skipLines = 0;

	protected ReaderRowMapper<T> rowMapper;

	protected AbstractReader(ReaderRowMapper<T> rowMapper) {
		super();
		this.rowMapper = rowMapper;
	}

	@Override
	public List<T> readAll(Reader reader) throws IOException {
		BufferedReader br = new BufferedReader(reader);
		try {
			return readAll(br);
		} finally {
			br.close();
		}
	}

	@Override
	public List<T> readAll(InputStream is) throws IOException {
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		try {
			return readAll(br);
		} finally {
			isr.close();
			br.close();
		}
	}

	@Override
	public void readWithCallback(InputStream is, FlatFileReaderCallback<T> callback) throws IOException {
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		try {
			readWithCallback(br, callback);
		} finally {
			isr.close();
			br.close();
		}
	}

	protected List<T> readAll(BufferedReader br) throws IOException {
		final List<T> beans = new ArrayList<>();
		readWithCallback(br, new FlatFileReaderCallback<T>() {
			@Override
			public void processRow(int rowIndex, String[] tokens, T bean) {
				beans.add(bean);
			}
		});

		return beans;
	}

	protected abstract void readWithCallback(BufferedReader br, FlatFileReaderCallback<T> callback) throws IOException;

	public int getSkipLines() {
		return skipLines;
	}

	public void setSkipLines(int skipLines) {
		this.skipLines = skipLines;
	}

	public void skipHeader() {
		setSkipLines(1);
	}

	public ReaderRowMapper<T> getRowMapper() {
		return rowMapper;
	}

	public void setRowMapper(ReaderRowMapper<T> rowMapper) {
		this.rowMapper = rowMapper;
	}

	@Override
	public ConverterRegistry getConverterRegistry() {
		return rowMapper.getConverterRegistry();
	}

}
