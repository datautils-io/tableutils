package com.datautils.table.excel.data;

import com.datautils.table.function.Tuple2;

public class Dimensions {

	private final Tuple2<Integer, Integer> start;

	private final Tuple2<Integer, Integer> end;

	public Dimensions(Tuple2<Integer, Integer> start, Tuple2<Integer, Integer> end) {
		this.start = start;
		this.end = end;
	}
}
