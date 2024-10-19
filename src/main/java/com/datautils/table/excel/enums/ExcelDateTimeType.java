package com.datautils.table.excel.enums;

public enum ExcelDateTimeType {

	DATE_TIME("DateTime"),
	TIME_DELTA("TimeDelta");

	private final String description;

	ExcelDateTimeType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
