package com.datautils.table.excel.cell;

import java.util.Objects;

public class Data implements CellType<Data>{

	private final Object value;

	public Data(Object value) {
		this.value = value;
	}

	public Object value() {
		return value;
	}

	@Override
	public Data getDefault() {
		return new Data(null);
	}

	@Override
	public Data copy() {
		return new Data(value);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Data that)) return false;
		return Objects.equals(value, that.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}
}
