package com.datautils.table.excel.cell;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Table<T extends CellType<T>> {

	private final String name;
	private final String sheetName;
	private final List<String> columns;
	private final Range<T> data;

	public Table(String name, String sheetName, List<String> columns, Range<T> data) {
		this.name = name;
		this.sheetName = sheetName;
		this.columns = new ArrayList<>(columns);
		this.data = data;
	}

	public List<String> columns() {
		return columns;
	}

	public Range<T> data() {
		return data;
	}

	public String name() {
		return name;
	}

	public String sheetName() {
		return sheetName;
	}

	public static <T extends CellType<T>> Range<T> from(Table<T> table) {
		return table.data;
	}

	@Override
	public String toString() {
		return String.format("Table[name=%s, sheetName=%s, columns=%s]",
				name, sheetName, columns);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Table)) return false;
		Table<?> table = (Table<?>) o;
		return Objects.equals(name, table.name) &&
				Objects.equals(sheetName, table.sheetName) &&
				Objects.equals(columns, table.columns) &&
				Objects.equals(data, table.data);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, sheetName, columns, data);
	}
}
