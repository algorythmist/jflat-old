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

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public final class ExcelHelper {
	
	private ExcelHelper() {
		super();
	}

	public static Workbook getWorkbook(String filename) throws IOException {
		String extension = getExtension(filename);
		if (extension == null || extension.isEmpty() || "xlsx".equals(extension)) {
			return new XSSFWorkbook();
		}
		if ("xls".equals(extension)) {
			return new HSSFWorkbook(); // xls
		}
		throw new IOException("Invlaid extension " + extension);
	}

	public static void writeWorkbook(Workbook workbook, String filename) throws IOException {
		String completeName;
		if (hasExtension(filename)) {
			completeName = filename;
		} else {
			completeName = filename + determineExtension(workbook);
		}
		FileOutputStream fileOut = new FileOutputStream(completeName);
		workbook.write(fileOut);
		fileOut.close();
	}

	private static String determineExtension(Workbook workbook) throws IOException {
		if (workbook instanceof HSSFWorkbook) {
			return ".xls";
		} else if (workbook instanceof XSSFWorkbook) {
			return ".xlsx";
		} else { // TODO support 3rd type
			throw new IOException("Unsupported workbook type");
		}
	}

	private static final boolean hasExtension(String filename) {
		return getExtension(filename) != null;
	}

	private static String getExtension(String filename) {
		int index = filename.lastIndexOf(".");
		if (index < 0) {
			return null;
		}
		String ext = filename.substring(index + 1);
		return ext.trim();
	}

}
