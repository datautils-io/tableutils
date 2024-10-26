package com.datautils.table.excel.reader;

import java.util.Optional;

import com.datautils.table.excel.data.ExcelDateTime;
import com.datautils.table.excel.enums.CellErrorType;

public interface CellType {

	boolean isEmpty();

	boolean isInt();

	boolean isFloat();

	boolean isBool();

	boolean isString();

	boolean isError();

	default boolean isDurationIso() {
		return false;
	}

	default boolean isDateTime() {
		return false;
	}

	default boolean isDateTimeIso() {
		return false;
	}

	Optional<Long> getInt();

	Optional<Double> getFloat();

	Optional<Boolean> getBool();

	Optional<String> getString();

	Optional<CellErrorType> getError();

	default Optional<ExcelDateTime> getDateTime() {
		return Optional.empty();
	}

	default Optional<String> getDateTimeIso() {
		return Optional.empty();
	}

	default Optional<String> getDurationIso() {
		return Optional.empty();
	}


}
