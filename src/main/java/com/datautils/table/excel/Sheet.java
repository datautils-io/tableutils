package com.datautils.table.excel;

import com.datautils.table.excel.enums.SheetType;
import com.datautils.table.excel.enums.SheetVisible;

public class Sheet {

	private String name;
	private SheetType type;
	private SheetVisible visible;

	public Sheet(String name, SheetType type, SheetVisible visible) {
		this.name = name;
		this.type = type;
		this.visible = visible;
	}

	public String name() {
		return name;
	}

	public Sheet setName(String name) {
		this.name = name;
		return this;
	}

	public SheetType type() {
		return type;
	}

	public Sheet setType(SheetType type) {
		this.type = type;
		return this;
	}

	public SheetVisible visible() {
		return visible;
	}

	public Sheet setVisible(SheetVisible visible) {
		this.visible = visible;
		return this;
	}
}
