package com.datautils.table.function;

import java.util.Objects;
import java.util.function.Function;

public class Tuple3<T1, T2, T3> extends Tuple2<T1, T2> {

	final T3 t3;

	Tuple3(T1 t1, T2 t2, T3 t3) {
		super(t1, t2);
		this.t3 = Objects.requireNonNull(t3, "t3");
	}

	public T3 getT3() {
		return t3;
	}

	public <R> Tuple3<R, T2, T3> mapT1(Function<T1, R> mapper) {
		return new Tuple3<>(mapper.apply(t1), t2, t3);
	}

	public <R> Tuple3<T1, R, T3> mapT2(Function<T2, R> mapper) {
		return new Tuple3<>(t1, mapper.apply(t2), t3);
	}

	public <R> Tuple3<T1, T2, R> mapT3(Function<T3, R> mapper) {
		return new Tuple3<>(t1, t2, mapper.apply(t3));
	}

	@Override
	public Object get(int index) {
		return switch (index) {
			case 0 -> t1;
			case 1 -> t2;
			case 2 -> t3;
			default -> null;
		};
	}

	@Override
	public Object[] toArray() {
		return new Object[]{t1, t2, t3};
	}

	@Override
	public int size() {
		return 3;
	}
}
