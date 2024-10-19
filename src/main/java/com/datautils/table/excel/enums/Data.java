package com.datautils.table.excel.enums;

public enum Data {

	INT("Integer"),
	FLOAT("Float"),
	STRING("String"),
	BOOL("Boolean"),
	DATE_TIME("DateTime"),
	DATE_TIME_ISO("DateTimeIso"),
	DURATION_ISO("DurationIso"),
	ERROR("Error"),
	EMPTY("Empty");

	private final String description;

	Data(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
