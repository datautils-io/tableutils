package com.datautils.table.excel.cell;

public interface CellType<T extends CellType<T>> extends Cloneable {

	T getDefault();

	T copy();

	boolean equals(Object obj);
}
