package com.datautils.table.excel.enums;

public enum DataRef {

	INT("Integer"),
	FLOAT("Float"),
	STRING("String"),
	SHARED_STRING("Shared String"),
	BOOL("Boolean"),
	DATE_TIME("DateTime"),
	DATE_TIME_ISO("DateTimeIso"),
	DURATION_ISO("DurationIso"),
	ERROR("Error"),
	EMPTY("Empty");

	private final String description;

	DataRef(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
