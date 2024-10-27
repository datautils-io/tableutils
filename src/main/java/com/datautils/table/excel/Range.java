package com.datautils.table.excel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.datautils.table.function.Tuple2;
import com.datautils.table.function.Tuples;

public class Range<T> {

	private final Tuple2<Integer, Integer> start;
	private final Tuple2<Integer, Integer> end;
	private final List<T> inner;
	private final int width;
	private final int height;

	public Range(Tuple2<Integer, Integer> start, Tuple2<Integer, Integer> end) {
		this.start = start;
		this.end = end;
		this.width = end.t1() - start.t1() + 1;
		this.height = end.t2() - start.t2() + 1;
		this.inner = new ArrayList<>(width * height);
	}

	public static <T> Range<T> empty() {
		return new Range<>(Tuples.of(0, 0), Tuples.of(0, 0));
	}

	public Optional<Tuple2<Integer, Integer>> getStart() {
		return isEmpty() ? Optional.empty() : Optional.of(start);
	}

	public Optional<Tuple2<Integer, Integer>> getEnd() {
		return isEmpty() ? Optional.empty() : Optional.of(end);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public boolean isEmpty() {
		return width <= 0 || height <= 0;
	}

	public void setValue(Tuple2<Integer, Integer> absolutePosition, T value) {
		int col = absolutePosition.t1() - start.t1();
		int row = absolutePosition.t2() - start.t2();
		inner.set(row * width + col, value);
	}

	public Optional<T> getValue(Tuple2<Integer, Integer> absolutePosition) {
		if (isOutOfBounds(absolutePosition)) {
			return Optional.empty();
		}
		int row = absolutePosition.t2() - start.t2();
		int col = absolutePosition.t1() - start.t1();
		return Optional.ofNullable(inner.get(row * width + col));
	}

	public List<T> getRow(int rowIndex) {
		int startIndex = rowIndex * getWidth();
		return inner.subList(startIndex, startIndex + getWidth());
	}

	public Tuple2<Integer, Integer> getSize() {
		return Tuples.of(height, width);
	}

	private boolean isOutOfBounds(Tuple2<Integer, Integer> position) {
		return position.t2() < start.t2() || position.t2() > end.t2() ||
				position.t1() < start.t1() || position.t1() > end.t1();
	}

}
