package com.datautils.table.excel.cell;

import java.util.ArrayList;
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
		return rowMap.getOrDefault(rowIndex, new ArrayList<>());
	}

	public int size() {
		return rowMap.size();
	}

	@Override
	public Iterator<Cell<T>> iterator() {
		return new Iterator<Cell<T>>() {
			private final Iterator<List<Cell<T>>> rowIterator = rowMap.values().iterator();
			private Iterator<Cell<T>> cellIterator = rowIterator.hasNext() ? rowIterator.next().iterator() : null;

			@Override
			public boolean hasNext() {
				while (cellIterator != null && !cellIterator.hasNext()) {
					if (rowIterator.hasNext()) {
						cellIterator = rowIterator.next().iterator();
					} else {
						cellIterator = null;
					}
				}
				return cellIterator != null;
			}

			@Override
			public Cell<T> next() {
				if (hasNext()) {
					return cellIterator.next();
				}
				throw new java.util.NoSuchElementException();
			}
		};
	}

	@Override
	public String toString() {
		return "Rows{" +
				"rowMap=" + rowMap +
				'}';
	}
}
