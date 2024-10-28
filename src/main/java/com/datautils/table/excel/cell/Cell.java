package com.datautils.table.excel.cell;

import java.util.Objects;

import com.datautils.table.function.Tuple2;
import com.datautils.table.function.Tuples;

public class Cell<T extends CellType<T>> implements CellType<Cell<T>> {

	private final Tuple2<Integer, Integer> position;

	private final T value;

	public Cell(Tuple2<Integer, Integer> position, T value) {
		this.position = position;
		this.value = value;
	}

	public Tuple2<Integer, Integer> position() {
		return position;
	}

	public T value() {
		return value;
	}

	@Override
	public Cell<T> getDefault() {
		return new Cell<>(Tuples.of(0, 0), value.getDefault());
	}

	@Override
	public Cell<T> copy() {
		return new Cell<>(Tuples.of(position.t1(), position.t2()), value.copy());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Cell<?> cell)) return false;
		return Objects.equals(position, cell.position)
				&& Objects.equals(value, cell.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(position, value);
	}

	@Override
	public String toString() {
		return String.format("Cell(pos=%s, val=%s)", position, value);
	}
}
