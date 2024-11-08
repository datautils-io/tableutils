package com.datautils.table.excel.enums;

public enum HeaderRow {

	FIRST_NON_EMPTY_ROW(-1),
	ROW(0);

	private Integer rowIndex;

	HeaderRow(int defaultIndex) {
		this.rowIndex = defaultIndex;
	}

	public static HeaderRow row(int index) {
		if (index < 0) {
			throw new IllegalArgumentException("Row index cannot be negative");
		}
		HeaderRow row = ROW;
		row.rowIndex = index;
		return row;
	}

	public Integer getRowIndex() {
		return rowIndex;
	}
}
