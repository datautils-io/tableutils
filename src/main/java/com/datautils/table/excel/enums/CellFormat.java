package com.datautils.table.excel.enums;

public enum CellFormat {

	OTHER("Other"),
	DATE_TIME("DateTime"),
	TIME_DELTA("TimeDelta");

	private final String description;

	CellFormat(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
