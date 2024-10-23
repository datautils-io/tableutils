package com.datautils.table.excel.reader;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.datautils.table.excel.Metadata;
import com.datautils.table.excel.Range;
import com.datautils.table.excel.Sheet;
import com.datautils.table.excel.enums.Data;
import com.datautils.table.excel.enums.HeaderRow;

public interface Reader<R> {

	Reader<R> withHeaderRow(HeaderRow headerRow);

	Metadata getMetadata();

	Range<Data> getWorksheetRange(String name) throws IOException;

	List<WorksheetData> getWorksheets() throws IOException;

	Range<String> getWorksheetFormula(String name) throws IOException;

	List<String> getSheetNames();

	List<Sheet> getSheetsMetadata();

	List<DefinedName> getDefinedNames();

	Optional<Range<Data>> getWorksheetRangeAt(int n) throws IOException;

}
