package com.datautils.table.function;

import java.io.Serial;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Function;

public class Tuple2<T1, T2> implements Iterable<Object>, Serializable {

	@Serial
	private static final long serialVersionUID = 2717732556038214217L;

	final T1 t1;
	final T2 t2;

	Tuple2(T1 t1, T2 t2) {
		this.t1 = Objects.requireNonNull(t1, "t1");
		this.t2 = Objects.requireNonNull(t2, "t2");
	}

	public T1 getT1() {
		return t1;
	}

	public T2 getT2() {
		return t2;
	}

	public <R> Tuple2<R, T2> mapT1(Function<T1, R> mapper) {
		return new Tuple2<>(mapper.apply(t1), t2);
	}

	public <R> Tuple2<T1, R> mapT2(Function<T2, R> mapper) {
		return new Tuple2<>(t1, mapper.apply(t2));
	}

	public Object get(int index) {
		return switch (index) {
			case 0 -> t1;
			case 1 -> t2;
			default -> null;
		};
	}

	public List<Object> toList() {
		return List.of(t1, t2);
	}

	public Object[] toArray() {
		return new Object[]{t1, t2};
	}

	public int size() {
		return 2;
	}

	@Override
	public Iterator<Object> iterator() {
		return new Iterator<>() {
			private int index = 0;

			@Override
			public boolean hasNext() {
				return index < size();
			}

			@Override
			public Object next() {
				if (!hasNext()) {
					throw new NoSuchElementException();
				}
				return get(index++);
			}
		};
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Tuple2<?, ?> tuple2 = (Tuple2<?, ?>) o;

		return t1.equals(tuple2.t1) && t2.equals(tuple2.t2);
	}

	@Override
	public int hashCode() {
		int result = size();
		result = 31 * result + t1.hashCode();
		result = 31 * result + t2.hashCode();
		return result;
	}

	@Override
	public final String toString() {
		return Tuples.tupleStringRepresentation(toArray()).insert(0, '[').append(']').toString();
	}

}
