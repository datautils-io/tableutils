package com.datautils.table.excel.reader;

import java.util.List;
import java.util.Optional;

import com.datautils.table.excel.Range;
import com.datautils.table.excel.enums.DataRef;

public interface ReaderRef<R> extends Reader<R> {

	Range<DataRef> getWorksheetRangeRef(String name);

	default Optional<Range<DataRef>> getWorksheetRangeAtRef(int n) {
		List<String> sheetNames = getSheetNames();
		if (n < 0 || n >= sheetNames.size()) {
			return Optional.empty();
		}
		String name = sheetNames.get(n);
		return Optional.of(getWorksheetRangeRef(name));
	}
}
