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
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.tecacet.jflat.WriterRowMapper;

public class PoiExcelWriter<T> {

	private final ExcelHelper excelHelper;

	private final WriterRowMapper<T> rowMapper;

	public PoiExcelWriter(String filename, WriterRowMapper<T> mapper) throws IOException {
		this(ExcelHelper.getWorkbook(ExcelHelper.getExtension(filename)), mapper);
	}

	public PoiExcelWriter(Workbook workbook, WriterRowMapper<T> mapper) {
		super();
		this.rowMapper = mapper;
		this.excelHelper = ExcelHelper.getHelper(workbook);
	}

	public void export(List<T> beans, String[] columnNames, String[] properties, Sheet sheet) throws IOException {
		Row headerRow = sheet.createRow(0);
		excelHelper.putValues(headerRow, columnNames);
		for (int rowindex = 0; rowindex < beans.size(); rowindex++) {
			T bean = beans.get(rowindex);
			writeOne(sheet, rowindex, bean);
		}

	}

	public void writeOne(Sheet sheet, int rowindex, T bean) {
		Row row = sheet.createRow(rowindex + 1);
		String[] values = rowMapper.getRow(bean);
		excelHelper.putValues(row, values);
	}

}
