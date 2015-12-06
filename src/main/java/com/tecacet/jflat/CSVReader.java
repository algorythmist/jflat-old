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
 * A reader for CSV files
 * 
 * @author Dimitri Papaioannou
 * 
 * @param <T>
 */
public class CSVReader<T> extends AbstractReader<T> {

	private static final char DEFAULT_QUOTE = '"';
	private static final char DEFAULT_DELIMITER = ',';
	
	private char quote = DEFAULT_QUOTE;
	private char delimiter = DEFAULT_DELIMITER;
	
	public CSVReader(ReaderRowMapper<T> mapper) {
		super(mapper);
	}

	public void setSeparator(char separator) {
		this.delimiter = separator;
	}

	public void setQuotechar(char quotechar) {
		this.quote = quotechar;
	}

	@Override
	protected void readWithCallback(BufferedReader br, FlatFileReaderCallback<T> callback) throws IOException {
		LineIterator lineIterator = new BufferedReaderLineIterator(br);
		CSVParser lineParser = new CSVParser(lineIterator);
		lineParser.setQuotechar(quote);
		lineParser.setSeparator(delimiter);
		for (int i = 0; i < skipLines; i++) {
			lineIterator.getNextLine();
		}
		int row = 0;
		String[] nextLineAsTokens = readNext(lineIterator, lineParser);
		while (nextLineAsTokens != null) {
			
			T bean = rowMapper.getRow(nextLineAsTokens, ++row);
			callback.processRow(row, nextLineAsTokens, bean);
			nextLineAsTokens = readNext(lineIterator, lineParser);
		}
	}
	
	private String[] readNext(LineIterator lineIterator, LineParser lineParser) throws IOException {
		String line = lineIterator.getNextLine();
		if (line == null) {
			return null;
		}
		return lineParser.parseLine(line);
	}

}
