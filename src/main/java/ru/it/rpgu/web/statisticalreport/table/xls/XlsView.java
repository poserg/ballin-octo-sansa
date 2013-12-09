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

import ru.it.rpgu.web.statisticalreport.ReportConstants;
import ru.it.rpgu.web.statisticalreport.table.ITableView;

/**
 * @author Sergey Popov
 *
 */
public class XlsView implements ITableView {

	private final Workbook wb;
	private final Map<String, CellStyle> styles;
	private final String reportName;
	private final Date fromDate;
	private final Date toDate;
	private Sheet sheet;
	private Row tableHeader;
	private List<String> columnNames;
	private int captionCount;
	
	public XlsView(String reportName, Date fromDate, Date toDate, boolean isCategory, boolean isLifeSituation) {
		this.fromDate = fromDate;
		this.toDate = toDate == null ? new Date() : toDate;
		
		captionCount = 1;
		if (isCategory)
			captionCount++;
		if (isLifeSituation)
			captionCount++;
		wb = new HSSFWorkbook();
		styles = XlsStyle.createStyles(wb);
		this.reportName = reportName;
		refresh();
	}
	
	@Override
	public void refresh() {
		columnNames = new ArrayList<String>();
		
		initSheet();
		initTitle();
        initDateCell();
		initDateInterval();
		createRow();
		tableHeader = createRow();
	}

	private void initDateInterval() {
		Row dateIntervalRow = sheet.createRow(5);
		Cell dateIntervalLabel = dateIntervalRow.createCell(0);
		dateIntervalLabel.setCellValue("Период:");
		Cell dateInterval = dateIntervalRow.createCell(captionCount);
		dateInterval.setCellStyle(styles.get(XlsStyle.TABLE_HEADER));

		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		if (fromDate != null && toDate != null) {
			dateInterval.setCellValue(formatter.format(fromDate) + " - " + formatter.format(toDate));
		} else if (fromDate == null) {
			dateInterval.setCellValue("по " + formatter.format(toDate));
		}
	}

	private void initDateCell() {
		Row dateRow = sheet.createRow(3);
		Cell dateLabelCell = dateRow.createCell(0);
		dateLabelCell.setCellValue("Дата формирования отчета:");
		Cell dateCell = dateRow.createCell(captionCount);
		dateLabelCell.setCellStyle(styles.get(XlsStyle.SUBTITLE_STYLE));
		Date today = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		dateCell.setCellValue(formatter.format(today));
		dateCell.setCellStyle(styles.get(XlsStyle.TEXT_STYLE));
	}

	private void initTitle() {
		Row titleRow = sheet.createRow(1);
		Cell titleCell = titleRow.createCell(0);
		titleCell.setCellValue(reportName);
		titleCell.setCellStyle(styles.get(XlsStyle.TITLE_STYLE));
		titleRow.setHeightInPoints(45);
		sheet.addMergedRegion(CellRangeAddress.valueOf("$A$2:$L$2"));
	}

	private void initSheet() {
		if (sheet != null) {
			int sheetIndex = wb.getSheetIndex(sheet);
			wb.removeSheetAt(sheetIndex);
		}
		sheet = wb.createSheet(reportName);
		sheet.setFitToPage(true);
		sheet.setDisplayGridlines(false);
	}

	private Row createRow() {
		return sheet.createRow(sheet.getLastRowNum() + 1);
	}

	@Override
	public void addColumn(String columnName) {
		addColumn(columnName, columnName.length() * 10);
	}

	private Cell createCell(Row row) {
		return row.createCell(row.getLastCellNum() == -1 ? 0 : row.getLastCellNum());
	}

	@Override
	public void addColumn(String columnName, int width) {
		createCell(columnName, tableHeader, XlsStyle.TABLE_HEADER);
		sheet.setColumnWidth(tableHeader.getLastCellNum() - 1, 50 * width);
		columnNames.add(columnName);
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
		
		String captionStyleName = null;
		String styleName = null;
		if ((row.getRowNum() - headerRowNum) % 2 == 0) {
			captionStyleName = XlsStyle.TABLE_CONTENT_CAPTION_EVEN;
			styleName = XlsStyle.TABLE_CONTENT_EVEN;
		} else {
			captionStyleName = XlsStyle.TABLE_CONTENT_CAPTION_ODD;
			styleName = XlsStyle.TABLE_CONTENT_ODD;
		}
		
		// Название
		if (cells.length >= captionCount) {
			for (int i = 0; i < captionCount; i++)
				createCell(cells[i].toString(), row, captionStyleName);
		}
		
		for (int i = captionCount; i < cells.length; i++) {
			createCell(cells[i].toString(), row, styleName);
		}
	}

	@Override
	public void addItem(String cell) {
		Row row = createRow();
		tableHeader = row;
		String styleName = XlsStyle.SUBTITLE_STYLE;
		createCell(cell, row, styleName);
		
		for (int i = 1; i < columnNames.size(); i++) {
			createCell(ReportConstants.EMPTY_STRING, tableHeader, styleName);
		}
	}

	private void createCell(String value, Row row, String styleName) {
		Cell createCell = createCell(row);
		createCell.setCellValue(value);
		createCell.setCellStyle(styles.get(styleName));
	}

	@Override
	public void addItem(List<Object> list) {
		Row footer = createRow();
		
		// Итого
		if (list.size() > 0) {
			createCell(list.get(0).toString(), footer, XlsStyle.TOTAL_TITLE);
		}
		
		for (int i = 1; i < list.size(); i++) {
			createCell(list.get(i).toString(), footer, XlsStyle.TOTAL);
		}
	}
}
