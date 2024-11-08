package com.datautils.table.excel.cell;

import java.util.HashMap;
import java.util.Map;

import com.datautils.table.function.Tuple2;
import com.datautils.table.function.Tuples;

public class CellStorage<T extends CellType<T>> {
	private static final int BLOCK_SIZE = 1024;
	private final Map<Integer, T[]> blocks;

	@SuppressWarnings("unchecked")
	public CellStorage() {
		this.blocks = new HashMap<>();
	}

	private Tuple2<Integer, Integer> getBlockPosition(int row, int col) {
		return Tuples.of(row / BLOCK_SIZE, col / BLOCK_SIZE);
	}

	public void setValue(int row, int col, T value) {
		Tuple2<Integer, Integer> blockPos = getBlockPosition(row, col);
		int blockKey = blockPos.t1() * BLOCK_SIZE + blockPos.t2();
		T[] block = blocks.computeIfAbsent(blockKey, k ->
				(T[]) new Object[BLOCK_SIZE * BLOCK_SIZE]);
		block[(row % BLOCK_SIZE) * BLOCK_SIZE + (col % BLOCK_SIZE)] = value;
	}
}
