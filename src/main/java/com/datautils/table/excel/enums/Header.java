package com.datautils.table.excel.enums;

public enum Header {

	NONE("No headers"),
	ALL("All headers"),
	CUSTOM("Custom headers");

	private final String description;

	Header(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
