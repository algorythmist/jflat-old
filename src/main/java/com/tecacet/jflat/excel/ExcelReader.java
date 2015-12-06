/*
 Copyright 2008 TecAceT Ltd.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */
package com.tecacet.jflat.excel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.tecacet.jflat.FlatFileReaderCallback;
import com.tecacet.jflat.ReaderRowMapper;
import com.tecacet.jflat.StructuredFileReader;

public abstract class ExcelReader<T> implements StructuredFileReader<T> {

	/**
	 * The default line to start reading.
	 */
	protected static final int DEFAULT_SKIP_LINES = 0;

	/**
	 * lines to skip before reading the first line
	 */
	protected int skipLines;

	protected ReaderRowMapper<T> rowMapper;

	/**
	 * Reads the entire file into a List. Each element is a bean of type T
	 * produced by the RowMapper
	 * 
	 * @return a List of String[], with each String[] representing a line of the
	 *         file.
	 * 
	 * @throws IOException
	 *             if bad things happen during the read
	 */
	public List<T> readAll() throws IOException {
		final List<T> allElements = new ArrayList<T>();
		readWithCallback(new FlatFileReaderCallback<T>() {

			public void processRow(int rowIndex, String[] tokens, T bean) {
				if (bean != null) {
					allElements.add(bean);
				}
			}
		});
		return allElements;

	}

	/**
	 * The number of lines to skip before reading a file
	 * 
	 * @return number of skipped lines
	 */
	public int getSkipLines() {
		return skipLines;
	}

	/**
	 * Set the number of lines to skip before reading a file.
	 * 
	 * @param skipLines
	 *            number of skipped lines
	 */
	public void setSkipLines(int skipLines) {
		this.skipLines = skipLines;
	}

}
