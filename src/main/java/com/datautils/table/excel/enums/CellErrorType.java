package com.datautils.table.excel.enums;

public enum CellErrorType {

	DIV0("#DIV/0!"),
	NA("#N/A"),
	NAME("#NAME?"),
	NULL("#NULL!"),
	NUM("#NUM!"),
	REF("#REF!"),
	VALUE("#VALUE!"),
	GETTING_DATA("#DATA!");

	private final String message;

	CellErrorType(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return message;
	}
}
