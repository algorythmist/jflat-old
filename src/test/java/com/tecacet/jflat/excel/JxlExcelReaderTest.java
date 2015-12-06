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

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.tecacet.jflat.BeanReaderRowMapper;
import com.tecacet.jflat.FlatFileReaderCallback;
import com.tecacet.jflat.ReaderRowMapper;
import com.tecacet.jflat.om.StockPrice;

public class JxlExcelReaderTest {

	@Test
	public void testReadAll() throws IOException {
		ReaderRowMapper<StockPrice> rowMapper = new BeanReaderRowMapper<StockPrice>(StockPrice.class,
				new String[] { "date", "openPrice", "closePrice", "volume" },
				new String[] { "Date", "Open", "Close", "Volume" });

		ExcelReader<StockPrice> reader = new JxlExcelReader<StockPrice>("testdata/prices.xls", rowMapper);
		// read all the prices
		List<StockPrice> prices = reader.readAll();
		assertEquals(253, prices.size());
		StockPrice price = prices.get(0);
		assertEquals(1550.87, price.getOpenPrice(), 0.0001);
		assertEquals(1577.03, price.getClosePrice(), 2);
		assertEquals(1521220000, price.getVolume());

		reader.readWithCallback(new FlatFileReaderCallback<StockPrice>() {

			@Override
			public void processRow(int rowIndex, String[] tokens, StockPrice bean) {

			}
		});
	}

}
