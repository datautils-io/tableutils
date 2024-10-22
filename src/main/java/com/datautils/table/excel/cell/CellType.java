package com.datautils.table.excel.cell;

import com.datautils.table.excel.enums.DataRef;

public interface CellType {

	Object getValue();

	void setValue(Object value);

	DataRef getDataRef();
}
