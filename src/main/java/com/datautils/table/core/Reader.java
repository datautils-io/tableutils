package com.datautils.table.core;

public interface Reader<R> {

	class ReaderError extends Exception {
		public ReaderError(String message) {
			super(message);
		}
	}

	/**
	 * Creates a new instance of the reader.
	 *
	 * @param reader the input source for reading data
	 * @throws ReaderError if an error occurs while creating the reader
	 */
	void newInstance(R reader) throws ReaderError;

	/**
	 * Set the header row (i.e., the first row to be read).
	 * If `headerRow` is null, the first non-empty row will be used as the header row.
	 *
	 * @param headerRow the header row to set
	 */
	void withHeaderRow(HeaderRow headerRow);

	/**
	 * Gets the VBA project associated with the workbook.
	 *
	 * @return the VBA project, or null if not present
	 */
	VbaProject getVbaProject();

	/**
	 * Enum to represent the row to use as the header.
	 */
	enum HeaderRow {
		FIRST_NON_EMPTY_ROW,
		ROW_INDEX
	}

	/**
	 * Class representing a VBA project.
	 */
	class VbaProject {
		// Implementation details for the VBA project
	}
}


