package com.datautils.table.excel.enums;

import com.datautils.table.excel.cell.CellType;

public enum Data implements CellType<Data> {

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

	@Override
	public Data getDefault() {
		return null;
	}

	@Override
	public Data copy() {
		return null;
	}
}
