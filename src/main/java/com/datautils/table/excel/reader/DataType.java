package com.datautils.table.excel.reader;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import com.datautils.table.excel.data.ExcelDateTime;
import com.datautils.table.excel.enums.CellErrorType;

public interface DataType {
	boolean isEmpty();

	boolean isInt();

	boolean isFloat();

	boolean isBool();

	boolean isString();

	boolean isError();

	boolean isDurationIso();

	boolean isDateTime();

	boolean isDateTimeIso();

	Optional<Long> getInt();

	Optional<Double> getFloat();

	Optional<Boolean> getBool();

	Optional<String> getString();

	Optional<ExcelDateTime> getDateTime();

	Optional<String> getDateTimeIso();

	Optional<String> getDurationIso();

	Optional<CellErrorType> getError();

	Optional<String> asString();

	Optional<Long> asLong();

	Optional<Double> asDouble();

	Optional<LocalDate> asDate();

	Optional<LocalTime> asTime();

	Optional<Duration> asDuration();

	Optional<LocalDateTime> asDateTime();
}
