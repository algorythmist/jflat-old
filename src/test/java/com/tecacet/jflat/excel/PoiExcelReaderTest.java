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

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.tecacet.jflat.TabularDataReaderCallback;
import com.tecacet.jflat.om.StockPrice;

public class PoiExcelReaderTest {

	@Test
	public void testReadNewExcel() throws IOException {
		readAll("testdata/prices.xlsx");
	}

	@Test
	public void testReadOldExcel() throws IOException {
		readAll("testdata/prices.xls");
	}

	private void readAll(String filename) throws IOException {
		
		ExcelReader<StockPrice> reader = new ExcelReader<StockPrice>(StockPrice.class,
				new String[] { "date", "openPrice", "closePrice", "volume" },
				new String[] { "Date", "Open", "Close", "Volume" });
		// read all the prices
		FileInputStream fis = new FileInputStream(filename);
		List<StockPrice> prices = reader.readAll(fis);
		assertEquals(253, prices.size());
		StockPrice price = prices.get(0);
		assertEquals(1550.87, price.getOpenPrice(), 0.0001);
		assertEquals(1577.03, price.getClosePrice(), 2);
		assertEquals(1521220000, price.getVolume());

		fis = new FileInputStream(filename);
		reader.readWithCallback(fis, new TabularDataReaderCallback<StockPrice>() {

			@Override
			public void processRow(int rowIndex, String[] tokens, StockPrice bean) {

			}
		});
	}

}
