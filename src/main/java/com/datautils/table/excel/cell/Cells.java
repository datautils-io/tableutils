package com.datautils.table.excel.cell;

import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;

import com.datautils.table.function.Tuple2;
import com.datautils.table.function.Tuples;

public class Cells<T extends CellType<T>> implements Iterable<Cell<T>> {

	private final Map<Tuple2<Integer, Integer>, Cell<T>> cellMap;
	private final int width;

	public Cells(int width) {
		this.cellMap = new HashMap<>();
		this.width = width;
	}

	public void addCell(Cell<T> cell) {
		Tuple2<Integer, Integer> pos = cell.position();
		if (pos.t2() >= width) {
			throw new IllegalArgumentException("Column index exceeds the width of the cells.");
		}
		cellMap.put(pos, cell);
	}

	public Cell<T> getCell(int row, int column) {
		return cellMap.get(Tuples.of(row, column));
	}

	public int getWidth() {
		return width;
	}

	public int size() {
		return cellMap.size();
	}

	public ListIterator<Cell<T>> listIterator() {
		return cellMap.values().stream().toList().listIterator();
	}

	@Override
	public Iterator<Cell<T>> iterator() {
		return cellMap.values().iterator();
	}

	@Override
	public String toString() {
		return "Cells{" +
				"width=" + width +
				", cellMap=" + cellMap +
				'}';
	}
}
