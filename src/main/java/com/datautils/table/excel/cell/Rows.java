package com.datautils.table.excel.cell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Rows<T extends CellType<T>> implements Iterable<Cell<T>> {

	private final Map<Integer, List<Cell<T>>> rowMap;

	public Rows() {
		rowMap = new HashMap<>();
	}

	public void addCell(int rowIndex, Cell<T> cell) {
		rowMap.computeIfAbsent(rowIndex, k -> new ArrayList<>()).add(cell);
	}

	public List<Cell<T>> getCells(int rowIndex) {
		return rowMap.getOrDefault(rowIndex, Collections.emptyList());
	}

	public int size() {
		return rowMap.size();
	}

	@Override
	public Iterator<Cell<T>> iterator() {
		return rowMap.values().stream()
				.flatMap(List::stream)
				.iterator();
	}

	@Override
	public String toString() {
		return "Rows{" +
				"rowMap=" + rowMap +
				'}';
	}
}
