package com.datautils.table.excel.cell;

import java.util.Objects;

public class StringCell implements CellType<StringCell> {

	private final String value;

	public StringCell(String value) {
		this.value = value != null ? value : "";
	}

	public String value() {
		return value;
	}

	@Override
	public StringCell getDefault() {
		return new StringCell("");
	}

	@Override
	public StringCell copy() {
		return new StringCell(value);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof StringCell that)) return false;
		return Objects.equals(value, that.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public String toString() {
		return value;
	}
}
