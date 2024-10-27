package com.datautils.table.excel.data;

import com.datautils.table.function.Tuple2;

public class Dimensions {

	private final Tuple2<Integer, Integer> start;

	private final Tuple2<Integer, Integer> end;

	public Dimensions(Tuple2<Integer, Integer> start, Tuple2<Integer, Integer> end) {
		this.start = start;
		this.end = end;
	}

	public boolean contains(Integer row, Integer col) {
		return row >= start.t1() && row <= end.t1() && col >= start.t2() && col <= end.t2();
	}

	public long length() {
		return (end.t1() - start.t1() + 1L) * (end.t2() - start.t2() + 1L);
	}
}
