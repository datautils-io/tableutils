package com.datautils.table.excel.cell;

import java.util.Objects;

public class DataRef<T> implements CellType<DataRef<T>> {
	private final T reference;

	public DataRef(T reference) {
		this.reference = reference;
	}

	public T getReference() {
		return reference;
	}

	@Override
	public DataRef<T> getDefault() {
		return new DataRef<>(null);
	}

	@Override
	public DataRef<T> copy() {
		return new DataRef<>(this.reference);
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) return true;
		if (!(other instanceof DataRef<?> dataRef)) return false;
		return Objects.equals(reference, dataRef.reference);
	}

	@Override
	public int hashCode() {
		return Objects.hash(reference);
	}

	@Override
	public String toString() {
		return String.format("DataRef(%s)", reference);
	}

	public static <T> DataRef<T> from(T value) {
		return new DataRef<>(value);
	}
}
