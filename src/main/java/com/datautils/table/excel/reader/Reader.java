package com.datautils.table.excel.reader;

import com.datautils.table.excel.enums.Data;
import com.datautils.table.excel.enums.HeaderRow;

public interface Reader<R> {

	// Error specific to file type
	class ReaderError extends Exception {
		public ReaderError(String message) {
			super(message);
		}
	}

	// Creates a new instance of the reader
	void initialize(R reader) throws ReaderError;

	// Set header row (i.e., first row to be read)
	void withHeaderRow(HeaderRow headerRow);


	// Read worksheet data in corresponding worksheet path
	Range<Data> worksheetRange(String name) throws ReaderError;

	// Fetch all worksheet data & paths
	Iterable<Range<Data>> worksheets() throws ReaderError;

	// Get all sheet names of this workbook, in workbook order
	Iterable<String> sheetNames();

	// Get the nth worksheet
	Range<Data> worksheetRangeAt(int n) throws ReaderError;

	// Get all defined names (Ranges names etc)
	Iterable<String> definedNames();

}
