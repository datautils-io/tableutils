package com.datautils.table;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class App {
	private static final Map<Integer, String> sharedStrings = new HashMap<>();

	public static void main(String[] args) {
		String file = ("");

		long startTimePoi = System.nanoTime();
		readExcelFilePoi(file);
		long endTimePoi = System.nanoTime();
		long durationPoi = endTimePoi - startTimePoi;
		System.out.println("Apache POI Duration: " + durationPoi / 1_000_000 + " ms");

		long startTimeCustom = System.nanoTime();
		readExcelFile(file);
		long endTimeCustom = System.nanoTime();
		long durationCustom = endTimeCustom - startTimeCustom;
		System.out.println("Custom Streaming Duration: " + durationCustom / 1_000_000 + " ms");
	}

	public static void readExcelFile(String filePath) {
		loadSharedStrings(filePath);

		parseSheet(filePath);
	}

	private static void loadSharedStrings(String filePath) {
		try (ZipInputStream zis = new ZipInputStream(Files.newInputStream(Paths.get(filePath)))) {
			ZipEntry entry;
			while ((entry = zis.getNextEntry()) != null) {
				if (entry.getName().equals("xl/sharedStrings.xml")) {
					DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
					DocumentBuilder builder = factory.newDocumentBuilder();
					Document doc = builder.parse(zis);
					doc.getDocumentElement().normalize();

					NodeList stringList = doc.getElementsByTagName("si");
					for (int i = 0; i < stringList.getLength(); i++) {
						Element stringElement = (Element) stringList.item(i);
						String value = stringElement.getTextContent();
						sharedStrings.put(i, value);
					}
				}
				zis.closeEntry();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void parseSheet(String filePath) {
		try (ZipInputStream zis = new ZipInputStream(Files.newInputStream(Paths.get(filePath)))) {
			ZipEntry entry;
			while ((entry = zis.getNextEntry()) != null) {
				if (entry.getName().equals("xl/worksheets/sheet1.xml")) { // Adjust for your sheet
					DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
					DocumentBuilder builder = factory.newDocumentBuilder();
					Document doc = builder.parse(zis);
					doc.getDocumentElement().normalize();

					NodeList rowList = doc.getElementsByTagName("row");
					for (int i = 0; i < rowList.getLength(); i++) {
						Element row = (Element) rowList.item(i);
						NodeList cellList = row.getElementsByTagName("c");
						for (int j = 0; j < cellList.getLength(); j++) {
							Element cell = (Element) cellList.item(j);
							String cellValue = getCellValue(cell);
							System.out.print(cellValue + "\t");
						}
						System.out.println();
					}
				}
				zis.closeEntry();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String getCellValue(Element cell) {
		String cellValue = "";
		String cellType = cell.getAttribute("t");

		if ("s".equals(cellType)) { // String type
			int index = Integer.parseInt(cell.getElementsByTagName("v").item(0).getTextContent());
			cellValue = sharedStrings.get(index);
		} else if ("n".equals(cellType)) { // Numeric type
			cellValue = cell.getElementsByTagName("v").item(0).getTextContent();
		} else { // Default case (could be blank or other types)
			cellValue = cell.getElementsByTagName("v").item(0).getTextContent();
		}

		return cellValue;
	}

	public static void readExcelFilePoi(String filePath) {
		try (FileInputStream fis = new FileInputStream(filePath);
		     Workbook workbook = getWorkbook(fis, filePath)) {

			Sheet sheet = workbook.getSheetAt(0);
			for (Row row : sheet) {
				for (Cell cell : row) {
					switch (cell.getCellType()) {
						case STRING:
							System.out.print(cell.getStringCellValue() + "\t");
							break;
						case NUMERIC:
							System.out.print(cell.getNumericCellValue() + "\t");
							break;
						case BOOLEAN:
							System.out.print(cell.getBooleanCellValue() + "\t");
							break;
						case FORMULA:
							System.out.print(cell.getCellFormula() + "\t");
							break;
						default:
							System.out.print(" \t");
							break;
					}
				}
				System.out.println();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static Workbook getWorkbook(FileInputStream fis, String filePath) throws IOException {
		if (filePath.endsWith("xlsx")) {
			return new XSSFWorkbook(fis); // For .xlsx files
		} else if (filePath.endsWith("xls")) {
			return new HSSFWorkbook(fis); // For .xls files
		} else {
			throw new IllegalArgumentException("The specified file is not an Excel file.");
		}
	}
}
