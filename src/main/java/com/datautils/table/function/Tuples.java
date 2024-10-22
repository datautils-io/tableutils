package com.datautils.table.function;

import java.util.function.Function;

@SuppressWarnings({"rawtypes"})
public abstract class Tuples implements Function {

	static final Tuples empty = new Tuples() {};

	public static Tuple2 fromArray(Object[] list) {

		if (list == null || list.length < 2) {
			throw new IllegalArgumentException("Array is null. Need 2-4 elements.");
		}

		return switch (list.length) {
			case 2 -> of(list[0], list[1]);
			case 3 -> of(list[0], list[1], list[2]);
			case 4 -> of(list[0], list[1], list[2], list[3]);
			default ->
					throw new IllegalArgumentException("Unexpected array length: " + list.length);
		};
	}

	public static <T1, T2> Tuple2<T1, T2> of(T1 t1, T2 t2) {
		return new Tuple2<>(t1, t2);
	}

	public static <T1, T2, T3> Tuple3<T1, T2, T3> of(T1 t1, T2 t2, T3 t3) {
		return new Tuple3<>(t1, t2, t3);
	}

	public static <T1, T2, T3, T4> Tuple4<T1, T2, T3, T4> of(T1 t1, T2 t2, T3 t3, T4 t4) {
		return new Tuple4<>(t1, t2, t3, t4);
	}

	@SuppressWarnings("unchecked")
	public static Function<Object[], Tuple2> fnAny() {
		return empty;
	}

	public static <R> Function<Object[], R> fnAny(final Function<Tuple2, R> delegate) {
		return objects -> delegate.apply(Tuples.fnAny().apply(objects));
	}

	@SuppressWarnings("unchecked")
	public static <T1, T2> Function<Object[], Tuple2<T1, T2>> fn2() {
		return empty;
	}


	@SuppressWarnings("unchecked")
	public static <T1, T2, T3> Function<Object[], Tuple3<T1, T2, T3>> fn3() {
		return empty;
	}

	public static <T1, T2, T3, R> Function<Object[], R> fn3(final Function<Tuple3<T1, T2, T3>, R> delegate) {
		return objects -> delegate.apply(Tuples.<T1, T2, T3>fn3().apply(objects));
	}

	@SuppressWarnings("unchecked")
	public static <T1, T2, T3, T4> Function<Object[], Tuple4<T1, T2, T3, T4>> fn4() {
		return empty;
	}

	public static <T1, T2, T3, T4, R> Function<Object[], R> fn4(final Function<Tuple4<T1, T2, T3, T4>, R> delegate) {
		return objects -> delegate.apply(Tuples.<T1, T2, T3, T4>fn4().apply(objects));
	}

	@Override
	public Tuple2 apply(Object o) {
		return fromArray((Object[]) o);
	}

	static StringBuilder tupleStringRepresentation(Object... values) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < values.length; i++) {
			Object t = values[i];
			if (i != 0) {
				sb.append(',');
			}
			if (t != null) {
				sb.append(t);
			}
		}
		return sb;
	}

	Tuples() {
	}
}

