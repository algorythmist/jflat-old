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
package com.tecacet.jflat;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Reads a flat file into a collection of beans. It uses a LineParser to parse
 * each line into tokens and a ReaderRowMapper to convert the tokens to beans.
 * 
 * @author Dimitri Papaioannou
 * 
 * @param <T>
 */
public class FlatFileReader<T> extends AbstractTabularDataReader<T> {

	protected LineIterator lineIterator;

	/**
	 * The default line to start reading.
	 */
	protected static final int DEFAULT_SKIP_LINES = 0;

	/**
	 * lines to skip before reading the first line
	 */
	protected int skipLines;

	protected LineParser lineParser;

	public FlatFileReader(LineParser lineParser, ReaderRowMapper<T> rowMapper) {
		super(rowMapper);
		this.lineParser = lineParser;
	}

	protected void readWithCallback(BufferedReader br, TabularDataReaderCallback<T> callback) throws IOException {
		int rowNumber = 1;
		for (int i = 0; i < skipLines; i++) {
			readNext(br);
			rowNumber++;
		}
		String[] nextLineAsTokens = readNext(br);
		while (nextLineAsTokens != null) {
			T bean = rowMapper.getRow(nextLineAsTokens, rowNumber);
			if (bean != null) {
				callback.processRow(rowNumber, nextLineAsTokens, bean);
			}
			rowNumber++;
			nextLineAsTokens = readNext(br);
		}
	}

	protected String[] readNext(BufferedReader bufferedReader) throws IOException {
		String line = bufferedReader.readLine();
		if (line == null) {
			return null;
		}
		return lineParser.parseLine(line);
	}

	public LineParser getLineParser() {
		return lineParser;
	}

	public void setLineParser(LineParser lineParser) {
		this.lineParser = lineParser;
	}

}
