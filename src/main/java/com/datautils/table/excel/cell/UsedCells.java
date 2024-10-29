package com.datautils.table.excel.cell;

import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;

import com.datautils.table.function.Tuple2;
import com.datautils.table.function.Tuples;

public class UsedCells<T extends CellType<T>> implements Iterable<Cell<T>> {

	private final Map<Tuple2<Integer, Integer>, Cell<T>> usedCellMap;
	private final int width;

	public UsedCells(int width) {
		this.usedCellMap = new HashMap<>();
		this.width = width;
	}

	public void addCell(Cell<T> cell) {
		Tuple2<Integer, Integer> pos = cell.position();
		if (pos.t2() >= width) {
			throw new IllegalArgumentException("Column index exceeds the width of the cells.");
		}
		usedCellMap.put(pos, cell);
	}

	public Cell<T> getCell(int row, int column) {
		return usedCellMap.get(Tuples.of(row, column));
	}

	public int getWidth() {
		return width;
	}

	public int size() {
		return usedCellMap.size();
	}

	public ListIterator<Cell<T>> listIterator() {
		return usedCellMap.values().stream().toList().listIterator();
	}

	@Override
	public Iterator<Cell<T>> iterator() {
		return usedCellMap.values().iterator();
	}

	@Override
	public String toString() {
		return "Cells{" +
				"width=" + width +
				", usedCellMap=" + usedCellMap +
				'}';
	}
}
