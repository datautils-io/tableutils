package com.datautils.table.excel.enums;

public enum ExcelDateTimeType {

	DATETIME("DateTime"),
	TIMEDELTA("TimeDelta");

	private final String description;

	ExcelDateTimeType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
