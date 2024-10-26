package com.datautils.table.excel.data;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

public class ExcelDateTime {

	private final double value;
	private final ExcelDateTimeType dateTimeType;
	private final boolean is1904;

	private static final double MS_MULTIPLIER = 24 * 60 * 60 * 1e3;
	private static final double EXCEL_1900_1904_DIFF = 1462.0;

	public enum ExcelDateTimeType {
		DATETIME,
		TIMEDELTA
	}

	public ExcelDateTime(double value, ExcelDateTimeType dateTimeType, boolean is1904) {
		this.value = value;
		this.dateTimeType = dateTimeType;
		this.is1904 = is1904;
	}

	public static ExcelDateTime fromValue(double value) {
		return new ExcelDateTime(value, ExcelDateTimeType.DATETIME, false);
	}

	public ExcelDateTimeType dateTimeType() {
		return dateTimeType;
	}

	public boolean is1904() {
		return is1904;
	}

	public double value() {
		return value;
	}

	public boolean isDuration() {
		return dateTimeType == ExcelDateTimeType.TIMEDELTA;
	}

	public boolean isDateTime() {
		return dateTimeType == ExcelDateTimeType.DATETIME;
	}

	public Optional<Duration> asDuration() {
		double ms = value * MS_MULTIPLIER;
		return Optional.of(Duration.ofMillis(Math.round(ms)));
	}

	public Optional<LocalDateTime> asLocalDateTime() {

		LocalDateTime excelEpoch = LocalDateTime.of(1899, 12, 30, 0, 0, 0, 0);
		double f = is1904 ? value + EXCEL_1900_1904_DIFF : value;
		f = f >= 60.0 ? f : f + 1.0;
		double ms = f * MS_MULTIPLIER;
		Duration excelDuration = Duration.ofMillis(Math.round(ms));
		return Optional.of(excelEpoch.plus(excelDuration));
	}

	@Override
	public String toString() {
		return "ExcelDateTime [value=" + value + ", dateTimeType=" + dateTimeType + "]";
	}
}
