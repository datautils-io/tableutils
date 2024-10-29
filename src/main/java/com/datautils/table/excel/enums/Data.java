package com.datautils.table.excel.enums;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import com.datautils.table.excel.cell.CellType;
import com.datautils.table.excel.data.ExcelDateTime;
import com.datautils.table.excel.reader.DataType;

public enum Data implements CellType<Data>, DataType {

	INT,
	FLOAT,
	STRING,
	BOOL,
	DATE_TIME,
	DATE_TIME_ISO,
	DURATION_ISO,
	ERROR,
	EMPTY;

	@Override
	public Data getDefault() {
		return null;
	}

	@Override
	public Data copy() {
		return null;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public boolean isInt() {
		return false;
	}

	@Override
	public boolean isFloat() {
		return false;
	}

	@Override
	public boolean isBool() {
		return false;
	}

	@Override
	public boolean isString() {
		return false;
	}

	@Override
	public boolean isError() {
		return false;
	}

	@Override
	public boolean isDurationIso() {
		return false;
	}

	@Override
	public boolean isDateTime() {
		return false;
	}

	@Override
	public boolean isDateTimeIso() {
		return false;
	}

	@Override
	public Optional<Long> getInt() {
		return Optional.empty();
	}

	@Override
	public Optional<Double> getFloat() {
		return Optional.empty();
	}

	@Override
	public Optional<Boolean> getBool() {
		return Optional.empty();
	}

	@Override
	public Optional<String> getString() {
		return Optional.empty();
	}

	@Override
	public Optional<ExcelDateTime> getDateTime() {
		return Optional.empty();
	}

	@Override
	public Optional<String> getDateTimeIso() {
		return Optional.empty();
	}

	@Override
	public Optional<String> getDurationIso() {
		return Optional.empty();
	}

	@Override
	public Optional<CellErrorType> getError() {
		return Optional.empty();
	}

	@Override
	public Optional<String> asString() {
		return Optional.empty();
	}

	@Override
	public Optional<Long> asLong() {
		return Optional.empty();
	}

	@Override
	public Optional<Double> asDouble() {
		return Optional.empty();
	}

	@Override
	public Optional<LocalDate> asDate() {
		return Optional.empty();
	}

	@Override
	public Optional<LocalTime> asTime() {
		return Optional.empty();
	}

	@Override
	public Optional<Duration> asDuration() {
		return Optional.empty();
	}

	@Override
	public Optional<LocalDateTime> asDateTime() {
		return Optional.empty();
	}
}
