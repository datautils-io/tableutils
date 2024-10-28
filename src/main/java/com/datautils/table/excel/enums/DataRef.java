package com.datautils.table.excel.enums;

import com.datautils.table.excel.cell.CellType;

public enum DataRef implements CellType<DataRef> {

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

	@Override
	public DataRef getDefault() {
		return null;
	}

	@Override
	public DataRef copy() {
		return null;
	}
}
