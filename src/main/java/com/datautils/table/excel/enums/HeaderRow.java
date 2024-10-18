package com.datautils.table.excel.enums;

public enum HeaderRow {

	FIRST_NON_EMPTY_ROW,
	ROW;

	private Integer rowIndex;

	private void setRowIndex(int index) {
		this.rowIndex = index;
	}

	public Integer getRowIndex() {
		return rowIndex;
	}
}
