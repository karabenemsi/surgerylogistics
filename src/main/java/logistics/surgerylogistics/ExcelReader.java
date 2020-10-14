package logistics.surgerylogistics;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	
	public LinkedList<Product> readProductsfromFile(String filePath){
		LinkedList<Product> products = new LinkedList<Product>();
		XSSFWorkbook workBook = null;
		try {
			workBook = new XSSFWorkbook(new FileInputStream(filePath));
			XSSFSheet sheet = workBook.getSheetAt(0);

			XSSFRow zeile;
			XSSFCell feldProduktart;
			XSSFCell feldID;
			XSSFCell feldMinAnzahl;
			XSSFCell feldMaxAnzahl;
			int i = 4;
			while (sheet.getLastRowNum() >= i) {
				zeile = sheet.getRow(i);
				if (zeile != null) {
					feldProduktart = zeile.getCell(1);
					if (feldProduktart != null) {
						feldID = zeile.getCell(2);
						feldMinAnzahl = zeile.getCell(3);
						feldMaxAnzahl = zeile.getCell(4);
						if (feldMinAnzahl != null && feldMaxAnzahl != null
								&& feldMinAnzahl.getCellType() == CellType.NUMERIC
								&& feldMaxAnzahl.getCellType() == CellType.NUMERIC) {
							String n = feldProduktart.getStringCellValue();
							String m = feldID.getStringCellValue();
							int min = (int) feldMinAnzahl.getNumericCellValue();
							int max = (int) feldMaxAnzahl.getNumericCellValue();
							products.add(new Product(n, m, min, max));
						}
					}
				}
				i++;
			}

		} catch (Exception e) {
			System.out.println("Error while reading Excel file");
			System.out.println(e);
		} finally {
			if (workBook != null)
				try {
					workBook.close();
				} catch (IOException e) {
					System.out.println("Couldn't close file");
					e.printStackTrace();
				}
		}
		
		return products;
	}

	public LinkedList<ChecklistEntry> readChecklistEntriesfromFile(String filePath, SurgeryType surgeryType){
		LinkedList<ChecklistEntry> entries = new LinkedList<ChecklistEntry>();
		XSSFWorkbook workBook = null;
		try {
			workBook = new XSSFWorkbook(new FileInputStream(filePath));
			XSSFSheet sheet = workBook.getSheetAt(0);

			XSSFRow zeile = sheet.getRow(1);

			int i = 9;
			int index = 0;
			boolean operationgefunden = false;
			while (i < zeile.getLastCellNum()) // operationgefunden == false ||
			{
				if (zeile.getCell(i) != null && surgeryType.toString().equals(zeile.getCell(i).getStringCellValue())) {
					operationgefunden = true;
					index = i;
				}
				i++;
			}

			if (operationgefunden == true) {
				i = 4;
				zeile = sheet.getRow(i);
				XSSFCell feldProduktart;
				XSSFCell feldAnzahl;

				while (sheet.getLastRowNum() >= i) // feldProduktart.getCellType() != CellType.BLANK)
				{
					if (zeile != null) {
						feldProduktart = zeile.getCell(1);
						feldAnzahl = zeile.getCell(index);
						if (feldAnzahl != null) // && feldAnzahl.getCellType() != CellType.BLANK)
						{
							String s = feldProduktart.getStringCellValue();
							int f = (int) feldAnzahl.getNumericCellValue();
							entries.add(new ChecklistEntry(Storage.getInstance().findProductById(s), f));
						}
					}
					i++;
					zeile = sheet.getRow(i);
				}
			} else {
				// Mitteilung
			}
		} catch (Exception e) {
			System.out.println("Error while reading Excel file");
			System.out.println(e);
		} finally {
			if (workBook != null)
				try {
					workBook.close();
				} catch (IOException e) {
					System.out.println("Couldn't close file");
					e.printStackTrace();
				}
		}
		
		return entries;
	}

}
