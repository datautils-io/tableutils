package com.datautils.table.excel.cell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.datautils.table.function.Tuple2;
import com.datautils.table.function.Tuples;

public class Range<T extends CellType<T>> {

	// (row, column)
	private final Tuple2<Integer, Integer> start;
	private final Tuple2<Integer, Integer> end;
	private final T[][] data;
	private final int width;
	private final int height;

	private final int startRow;
	private final int startCol;
	private final int endRow;
	private final int endCol;

	@SuppressWarnings("unchecked")
	private Range(Tuple2<Integer, Integer> start, Tuple2<Integer, Integer> end, boolean validate) {
		if (validate) {
			validateBounds(start, end);
		}

		this.start = start;
		this.end = end;
		this.startRow = start.t2();
		this.startCol = start.t1();
		this.endRow = end.t2();
		this.endCol = end.t1();
		this.width = endCol - startCol + 1;
		this.height = endRow - startRow + 1;

		this.data = width <= 0 || height <= 0 ? null :
				(T[][]) new Object[height][width];
	}

	public Range(Tuple2<Integer, Integer> start, Tuple2<Integer, Integer> end) {
		this(start, end, true);
	}

	private static void validateBounds(Tuple2<Integer, Integer> start, Tuple2<Integer, Integer> end) {
		if (start.t1() > end.t1() || start.t2() > end.t2()) {
			throw new IllegalArgumentException("Invalid range bounds");
		}
	}

	public static <T extends CellType<T>> Range<T> fromSparse(Map<Tuple2<Integer, Integer>, T> sparseData) {
		if (sparseData == null || sparseData.isEmpty()) {
			return empty();
		}

		// Use streams for parallel processing of large datasets
		int[] bounds = sparseData.keySet().parallelStream()
				.reduce(new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE},
						(acc, pos) -> {
							acc[0] = Math.min(acc[0], pos.t1()); // minCol
							acc[1] = Math.min(acc[1], pos.t2()); // minRow
							acc[2] = Math.max(acc[2], pos.t1()); // maxCol
							acc[3] = Math.max(acc[3], pos.t2()); // maxRow
							return acc;
						},
						(acc1, acc2) -> {
							acc1[0] = Math.min(acc1[0], acc2[0]);
							acc1[1] = Math.min(acc1[1], acc2[1]);
							acc1[2] = Math.max(acc1[2], acc2[2]);
							acc1[3] = Math.max(acc1[3], acc2[3]);
							return acc1;
						});

		Range<T> range = new Range<>(
				Tuples.of(bounds[0], bounds[1]),
				Tuples.of(bounds[2], bounds[3]),
				false
		);

		sparseData.forEach((pos, value) -> {
			int row = pos.t2() - range.startRow;
			int col = pos.t1() - range.startCol;
			range.data[row][col] = value;
		});

		return range;
	}

	public static <T extends CellType<T>> Range<T> empty() {
		return new Range<>(Tuples.of(0, 0), Tuples.of(-1, -1), false);
	}

	// Optimized value access with cached bounds
	public void setValue(Tuple2<Integer, Integer> pos, T value) {
		int row = pos.t2() - startRow;
		int col = pos.t1() - startCol;
		if (row < 0 || row >= height || col < 0 || col >= width) {
			throw new IllegalArgumentException("Position out of bounds");
		}
		data[row][col] = value;
	}

	public Optional<T> getValue(Tuple2<Integer, Integer> pos) {
		int row = pos.t2() - startRow;
		int col = pos.t1() - startCol;
		if (row < 0 || row >= height || col < 0 || col >= width) {
			return Optional.empty();
		}
		return Optional.ofNullable(data[row][col]);
	}

	public List<T> getRow(int rowIndex) {
		if (rowIndex < 0 || rowIndex >= height) {
			throw new IllegalArgumentException("Row index out of bounds");
		}
		return new ArrayList<>(Arrays.asList(data[rowIndex]));
	}

	public List<T> getColumn(int colIndex) {
		if (colIndex < 0 || colIndex >= width) {
			throw new IllegalArgumentException("Column index out of bounds");
		}
		ArrayList<T> column = new ArrayList<>(height);
		for (int i = 0; i < height; i++) {
			column.add(data[i][colIndex]);
		}
		return column;
	}

	public Stream<Cell<T>> cellStream() {
		if (isEmpty()) {
			return Stream.empty();
		}

		return IntStream.range(0, height)
				.boxed()
				.flatMap(row ->
						IntStream.range(0, width)
								.mapToObj(col -> {
									T value = data[row][col];
									return value == null ? null :
											new Cell<>(Tuples.of(startCol + col, startRow + row), value);
								})
								.filter(Objects::nonNull)
				);
	}

	public boolean isEmpty() {
		return data == null;
	}

	public Tuple2<Integer, Integer> getSize() {
		return Tuples.of(height, width);
	}

	private boolean isOutOfBounds(Tuple2<Integer, Integer> pos) {
		int row = pos.t2();
		int col = pos.t1();
		return row < startRow || row > endRow || col < startCol || col > endCol;
	}

	@Override
	public String toString() {
		if (isEmpty()) {
			return "Range(empty)";
		}

		return String.format("Range(%d×%d, %s -> %s)",
				width, height, start, end);
	}
}
