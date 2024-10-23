package com.datautils.table.function;

import java.io.Serial;
import java.util.Objects;
import java.util.function.Function;

public class Tuple4<T1, T2, T3, T4> extends Tuple3<T1, T2, T3> {

	@Serial
	private static final long serialVersionUID = 7438063693758398864L;

	final T4 t4;

	Tuple4(T1 t1, T2 t2, T3 t3, T4 t4) {
		super(t1, t2, t3);
		this.t4 = Objects.requireNonNull(t4, "t4");
	}

	public T4 getT4() {
		return t4;
	}

	@Override
	public <R> Tuple4<R, T2, T3, T4> mapT1(Function<T1, R> mapper) {
		return new Tuple4<>(mapper.apply(t1), t2, t3, t4);
	}

	@Override
	public <R> Tuple4<T1, R, T3, T4> mapT2(Function<T2, R> mapper) {
		return new Tuple4<>(t1, mapper.apply(t2), t3, t4);
	}

	@Override
	public <R> Tuple4<T1, T2, R, T4> mapT3(Function<T3, R> mapper) {
		return new Tuple4<>(t1, t2, mapper.apply(t3), t4);
	}

	public <R> Tuple4<T1, T2, T3, R> mapT4(Function<T4, R> mapper) {
		return new Tuple4<>(t1, t2, t3, mapper.apply(t4));
	}

	@Override
	public Object get(int index) {
		return switch (index) {
			case 0 -> t1;
			case 1 -> t2;
			case 2 -> t3;
			case 3 -> t4;
			default -> null;
		};
	}

	@Override
	public Object[] toArray() {
		return new Object[]{t1, t2, t3, t4};
	}

	@Override
	public int size() {
		return 4;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Tuple4<?, ?, ?, ?> tuple4)) return false;
		if (!super.equals(o)) return false;

		return t4.equals(tuple4.t4);
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + t4.hashCode();
		return result;
	}
}
