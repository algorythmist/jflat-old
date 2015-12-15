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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.tecacet.jflat.WriterRowMapper;

public class PoiExcelWriter<T> {

	private String dateFormat = "m/d/yy h:mm";
	private final Workbook workbook;
	private final WriterRowMapper<T> rowMapper;
	private final CreationHelper createHelper;
	private final String filename;

	public PoiExcelWriter(String filename, WriterRowMapper<T> mapper) throws IOException {
		this.workbook = ExcelHelper.getWorkbook(filename);
		this.rowMapper = mapper;
		this.createHelper = workbook.getCreationHelper();
		this.filename = filename;
	}

	public void export(List<T> beans, String[] columnNames) throws IOException {
		Sheet sheet = workbook.createSheet();
		Row headerRow = sheet.createRow(0);
		putValues(headerRow, columnNames);
		for (int rowindex = 0; rowindex < beans.size(); rowindex++) {
			T bean = beans.get(rowindex);
			writeOne(sheet, rowindex, bean);
		}
		ExcelHelper.writeWorkbook(workbook, filename);
	}

	public void writeOne(Sheet sheet, int rowindex, T bean) {
		Row row = sheet.createRow(rowindex + 1);
		String[] values = rowMapper.getRow(bean);
		putValues(row, values);
	}

	public Cell[] putValues(Row row, Object[] values) {
		Cell[] cells = new Cell[values.length];
		for (int i = 0; i < values.length; i++) {
			cells[i] = row.createCell(i);
			setCellValue(cells[i], values[i]); // TODO
		}
		return cells;
	}

	private void setCellValue(Cell cell, Object object) {

		if (object == null) {
			cell.setCellValue("");
			return;
		}
		if (object instanceof Number) {
			cell.setCellValue(((Number) object).doubleValue());
		} else if (object instanceof Boolean) {
			cell.setCellValue((Boolean) object);
		} else if (object instanceof String) {
			cell.setCellValue(createHelper.createRichTextString((String) object));
		} else if (object instanceof Date) {
			cell.setCellValue((Date) object);
			cell.setCellStyle(getDateStyle());
			// TODO handle Joda
		} else if (object instanceof Calendar) {
			cell.setCellValue((Calendar) object);
			cell.setCellStyle(getDateStyle());
		} else {
			cell.setCellValue(object.toString());
		}
	}

	private CellStyle getDateStyle() {
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setDataFormat(createHelper.createDataFormat().getFormat(dateFormat));
		return cellStyle;
	}

	/**
	 * Creates a cell and aligns it a certain way.
	 * 
	 * @param wb
	 *            the workbook
	 * @param row
	 *            the row to create the cell in
	 * @param column
	 *            the column number to create the cell in
	 * @param halign
	 *            the horizontal alignment for the cell.
	 */
	//TODO use it or lose it
	private Cell createCell(Row row, short column, short halign, short valign) {
		Cell cell = row.createCell(column);
		// cell.setCellValue("Align It");
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(halign);
		cellStyle.setVerticalAlignment(valign);
		cell.setCellStyle(cellStyle);
		return cell;
	}

}
