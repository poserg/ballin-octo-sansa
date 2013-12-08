package ru.it.rpgu.web.statisticalreport.table.xls;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import ru.it.rpgu.web.statisticalreport.table.ITableView;

/**
 * @author Sergey Popov
 *
 */
public class XlsView implements ITableView {

	private final Workbook wb;
	private final Map<String, CellStyle> styles;
	private Sheet sheet;
	private Cell dateInterval;
	private Cell titleCell;
	private Row tableHeader;
	private List<String> columnNames;
	
	public XlsView() {
		wb = new HSSFWorkbook();
		styles = XlsStyle.createStyles(wb);
		refresh();
	}
	
	@Override
	public void refresh() {
		columnNames = new ArrayList<String>();
		
		if (sheet != null) {
			int sheetIndex = wb.getSheetIndex(sheet);
			wb.removeSheetAt(sheetIndex);
		}
		sheet = wb.createSheet();
		sheet.setFitToPage(true);
		sheet.setDisplayGridlines(false);
		
		Row titleRow = sheet.createRow(1);
		titleCell = titleRow.createCell(0);
		titleCell.setCellStyle(styles.get(XlsStyle.TITLE_STYLE));
		titleRow.setHeightInPoints(45);
		sheet.addMergedRegion(CellRangeAddress.valueOf("$A$2:$L$2"));
		
        // :TODO Зависит от наличия жизненной ситуации и категории
        int datePosition = 1;
		
		// Date
		Row dateRow = sheet.createRow(3);
		Cell dateLabelCell = dateRow.createCell(0);
		dateLabelCell.setCellValue("Дата формирования отчета:");
		Cell dateCell = dateRow.createCell(datePosition);
		dateLabelCell.setCellStyle(styles.get(XlsStyle.SUBTITLE_STYLE));
		Date today = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		dateCell.setCellValue(formatter.format(today));
		dateCell.setCellStyle(styles.get(XlsStyle.TEXT_STYLE));
		
		// Date interval
		Row dateIntervalRow = sheet.createRow(5);
		Cell dateIntervalLabel = dateIntervalRow.createCell(0);
		dateIntervalLabel.setCellValue("Период:");
		dateInterval = dateIntervalRow.createCell(datePosition);
		dateInterval.setCellStyle(styles.get(XlsStyle.TABLE_HEADER));
		
		createRow();
		tableHeader = createRow();
	}

	private Row createRow() {
		return sheet.createRow(sheet.getLastRowNum() + 1);
	}

	private void setTitle(String title) {
		titleCell.setCellValue(title);
	}

	private void setDateInterval(Date today) {
		SimpleDateFormat formatter2 = new SimpleDateFormat("dd.MM.yyyy");
		String date1 = formatter2.format(today);
		String date2 = formatter2.format(today);
		dateInterval.setCellValue(date1 + "-" + date2);
	}

	@Override
	public void addColumn(String columnName) {
		Cell cell = createCell(tableHeader);
		cell.setCellValue(columnName);
		cell.setCellStyle(styles.get(XlsStyle.TABLE_HEADER));
		columnNames.add(columnName);
	}

	private Cell createCell(Row row) {
		return row.createCell(row.getLastCellNum());
	}

	@Override
	public void addColumn(String columnName, int width) {
		addColumn(columnName);
	}
	
	@Override
	public void setFooter(List<Object> row) {
		addItem(row);
		createBottomTableHeader();
	}

	private void createBottomTableHeader() {
		Row bottomHeader = createRow();
		for (String columnName : columnNames) {
			Cell cell = createCell(bottomHeader);
			cell.setCellValue(columnName);
			cell.setCellStyle(styles.get(XlsStyle.TABLE_HEADER));
		}
	}

	public byte[] getFile() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			wb.write(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out.toByteArray();
	}

	@Override
	public void addItem(Object[] cells) {
		int headerRowNum = tableHeader.getRowNum();
		
		Row row = createRow();
		
		CellStyle style = styles.get((row.getRowNum() - headerRowNum) % 2 == 0 ? XlsStyle.TABEL_CONTENT_ODD : XlsStyle.TABLE_CONTENT_EVEN);
		
		for (int i = 0; i < cells.length; i++) {
			createCell(cells[i].toString(), row, style);
		}
	}

	@Override
	public void addItem(String cell) {
		Row row = createRow();
		createCell(cell, row, styles.get(XlsStyle.SUBTITLE_STYLE));
	}

	private void createCell(String value, Row row, CellStyle cellStyle) {
		Cell createCell = createCell(row);
		createCell.setCellValue(value);
		createCell.setCellStyle(cellStyle);
	}

	@Override
	public void addItem(List<Object> list) {
		Row footer = createRow();
		
		// Итого
		if (list.size() > 0) {
			Cell cell = createCell(footer);
			cell.setCellValue(list.get(0).toString());
			cell.setCellStyle(styles.get(XlsStyle.TOTAL_TITLE));
		}
		
		for (int i = 1; i < list.size(); i++) {
			Cell cell = createCell(footer);
			cell.setCellValue(list.get(i).toString());
			cell.setCellStyle(styles.get(XlsStyle.TOTAL));
		}
	}
}
