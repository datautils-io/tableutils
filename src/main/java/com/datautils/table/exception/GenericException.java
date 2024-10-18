package com.datautils.table.exception;

import java.io.Serial;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GenericException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = -38961709786424018L;

	private final String errorType;

	private final String exceptionId;

	public GenericException(String errorType, String message) {

		super(message);
		this.errorType = errorType;
		this.exceptionId = uniqueId();
	}

	public GenericException(String errorType, String message, Throwable ex) {

		super(message, ex);
		this.errorType = errorType;
		this.exceptionId = uniqueId();
	}

	public GenericException(String errorType, String exceptionId, String msg, Throwable e) {

		super(msg, e);
		this.errorType = errorType;
		this.exceptionId = exceptionId;
	}

	public String getErrorType() {
		return errorType;
	}

	public String getExceptionId() {
		return this.exceptionId;
	}

	public static String uniqueId() {

		return (new SimpleDateFormat("yyyy-MM-dd")).format(Calendar.getInstance()
				.getTime()) + "-" + Long.toHexString(System.currentTimeMillis() % (1000 * 60 * 60 * 24l));
	}

	public GenericExceptionData toExceptionData() {

		String st = Stream.of(this.getStackTrace())
				.map(StackTraceElement::toString)
				.collect(Collectors.joining("\n"));

		Throwable t = this.getCause();
		if (t != null) {
			st += "---------- Cause " + t.getMessage() + "\n" + Stream.of(t.getStackTrace())
					.map(StackTraceElement::toString)
					.collect(Collectors.joining("\n"));
		}

		return new GenericExceptionData().setExceptionId(this.exceptionId)
				.setMessage(this.getMessage())
				.setStackTrace(st)
				.setDebugMessage((t == null ? this : t).getMessage());
	}

	public static class GenericExceptionData {

		private String exceptionId;
		private String message;
		private String stackTrace;
		private String debugMessage;
	}
}

