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

	public Range(Tuple2<Integer, Integer> start, Tuple2<Integer, Integer> end) {
		this.start = start;
		this.end = end;
		int width = (end.getT1() - start.getT1() + 1);
		int height = (end.getT2() - start.getT2() + 1);
		this.inner = new ArrayList<>(width * height);
		for (int i = 0; i < width * height; i++) {
			this.inner.add(null);
		}
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
		return isEmpty() ? 0 : (end.getT1() - start.getT1() + 1);
	}

	public int getHeight() {
		return isEmpty() ? 0 : (end.getT2() - start.getT2() + 1);
	}

	public boolean isEmpty() {
		return inner.isEmpty();
	}

	public void setValue(Tuple2<Integer, Integer> absolutePosition, T value) {
		int col = absolutePosition.getT1() - start.getT1();
		int row = absolutePosition.getT2() - start.getT2();
		int index = row * getWidth() + col;
		inner.set(index, value);
	}

	public Optional<T> getValue(Tuple2<Integer, Integer> absolutePosition) {
		if (absolutePosition.getT2() < start.getT2() || absolutePosition.getT2() > end.getT2() ||
				absolutePosition.getT1() < start.getT1() || absolutePosition.getT1() > end.getT1()) {
			return Optional.empty();
		}
		int row = absolutePosition.getT2() - start.getT2();
		int col = absolutePosition.getT1() - start.getT1();
		int index = row * getWidth() + col;
		return Optional.ofNullable(inner.get(index));
	}

	public List<T> getRow(int rowIndex) {
		int startIndex = rowIndex * getWidth();
		return inner.subList(startIndex, startIndex + getWidth());
	}

	public Tuple2<Integer, Integer> getSize() {
		return Tuples.of(getHeight(), getWidth());
	}
}
