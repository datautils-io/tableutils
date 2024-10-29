package com.datautils.table.excel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Metadata {

	private List<Sheet> sheets;
	private Map<String, String> names;

	public Metadata() {
		this.sheets = new ArrayList<>();
		this.names = new HashMap<>();
	}

	public List<Sheet> sheets() {
		return sheets;
	}

	public Metadata setSheets(List<Sheet> sheets) {
		this.sheets = sheets;
		return this;
	}

	public Map<String, String> getNames() {
		return names;
	}

	public Metadata setNames(Map<String, String> names) {
		this.names = names;
		return this;
	}
}
