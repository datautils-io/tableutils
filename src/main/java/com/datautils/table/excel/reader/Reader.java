package com.datautils.table.excel.reader;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.datautils.table.excel.Metadata;
import com.datautils.table.excel.Range;
import com.datautils.table.excel.Sheet;
import com.datautils.table.excel.enums.Data;
import com.datautils.table.excel.enums.HeaderRow;
import com.datautils.table.function.Tuple2;

public interface Reader<R> {

	Reader<R> withHeaderRow(HeaderRow headerRow);

	Metadata getMetadata();

	Range<Data> getWorksheetRange(String name) throws IOException;

	List<Tuple2<String, Range<Data>>> getWorksheets() throws IOException;

	Range<String> getWorksheetFormula(String name) throws IOException;

	default List<String> getSheetNames() {
		return getSheetsMetadata().stream()
				.map(Sheet::name)
				.toList();
	}

	default List<Sheet> getSheetsMetadata() {
		return getMetadata().sheets();
	}

	default Map<String, String> getDefinedNames() {
		return getMetadata().names();
	}

	default Optional<Range<Data>> getWorksheetRangeAt(int n) throws IOException {
		List<Sheet> sheets = getSheetsMetadata();
		return (n >= 0 && n < sheets.size())
				? Optional.of(getWorksheetRange(sheets.get(n).name()))
				: Optional.empty();
	}
}
