package com.datautils.table.excel.data;

public interface DataType {

	boolean isEmpty();

	boolean isInt();

	boolean isFloat();

	boolean isBool();

	boolean isString();

	boolean isError();

	Integer getInt();

	Float getFloat();

	Boolean getBool();

	String getString();

	String getError();

	String asString();

	Integer asInteger();

	Float asFloat();
}
