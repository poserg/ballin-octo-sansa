package ru.it.rpgu.web.statisticalreport.table.xls;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FontUnderline;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;

class XlsStyle {

	public static final String TOTAL = "total";
	public static final String TOTAL_TITLE = "total_title";
	public static final String TABLE_CONTENT_EVEN = "table_content_even";
	public static final String TABEL_CONTENT_ODD = "tabel_content_odd";
	public static final String TABLE_HEADER = "table_header";
	public static final String TEXT_STYLE = "text_style";
	public static final String SUBTITLE_STYLE = "subtitle_style";
	public static final String TITLE_STYLE = "title_style";

	
	public static Map<String, CellStyle> createStyles(Workbook wb) {
		Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
		
		CellStyle style;
		
		Font titleFont = wb.createFont();
		titleFont.setUnderline(FontUnderline.SINGLE.getByteValue());
		titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		titleFont.setFontHeightInPoints((short) 16);
		style = wb.createCellStyle();
		style.setFont(titleFont);
		styles.put(TITLE_STYLE, style);
		
		Font subtitleFont = wb.createFont();
		subtitleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		subtitleFont.setFontHeightInPoints((short) 11);
		CellStyle subtitleStyle = wb.createCellStyle();
		subtitleStyle.setFont(subtitleFont);
		styles.put(SUBTITLE_STYLE, subtitleStyle);
		
		Font textFont = wb.createFont();
		textFont.setFontHeightInPoints((short) 11);
		CellStyle textStyle = wb.createCellStyle();
		textStyle.setFont(textFont);
		styles.put(TEXT_STYLE, textStyle);
		
		
		CellStyle tableHeaderStyle = wb.createCellStyle();
		tableHeaderStyle.setFont(subtitleFont);
		tableHeaderStyle.setAlignment(CellStyle.ALIGN_CENTER);
		setBorders(tableHeaderStyle);
		setBackgroud(tableHeaderStyle, IndexedColors.GREY_40_PERCENT);
		styles.put(TABLE_HEADER, tableHeaderStyle);
		
		CellStyle subtableTitle = wb.createCellStyle();
		subtableTitle.setFont(subtitleFont);
		setBackgroud(subtableTitle, IndexedColors.GREY_25_PERCENT);
		setBorders(subtableTitle);
		
		CellStyle tableContentOddStyle = wb.createCellStyle();
		tableContentOddStyle.setFont(textFont);
		setBorders(tableContentOddStyle);
		styles.put(TABEL_CONTENT_ODD, tableContentOddStyle);
		
		CellStyle tableContentEvenStyle = wb.createCellStyle();
		tableContentEvenStyle.setFont(textFont);
		setBackgroud(tableContentEvenStyle, IndexedColors.GREY_25_PERCENT);
		setBorders(tableContentEvenStyle);
		styles.put(TABLE_CONTENT_EVEN, tableContentEvenStyle);
		
		CellStyle totalTitleCellStyle = wb.createCellStyle();
		totalTitleCellStyle.setFont(subtitleFont);
		totalTitleCellStyle.setAlignment(CellStyle.ALIGN_RIGHT);
		setBorders(totalTitleCellStyle, CellStyle.BORDER_DOUBLE);
		setBackgroud(totalTitleCellStyle, IndexedColors.GREY_40_PERCENT);
		styles.put(TOTAL_TITLE, totalTitleCellStyle);
		
		CellStyle totalCellStyle = wb.createCellStyle();
		totalCellStyle.setFont(subtitleFont);
		totalCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		setBorders(totalCellStyle, CellStyle.BORDER_DOUBLE);
		setBackgroud(totalCellStyle, IndexedColors.GREY_40_PERCENT);
		styles.put(TOTAL, totalCellStyle);
		
		return styles;
	}

	/**
	 * @param tableHeaderStyle
	 */
	private static void setBackgroud(CellStyle tableHeaderStyle, IndexedColors color) {
		tableHeaderStyle.setFillForegroundColor(color.getIndex());
		tableHeaderStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
	}

	/**
	 * @param tableHeaderStyle
	 */
	private static void setBorders(CellStyle tableHeaderStyle) {
		setBorders(tableHeaderStyle, CellStyle.BORDER_MEDIUM);
	}
	
	private static void setBorders(CellStyle tableHeaderStyle, short style) {
		tableHeaderStyle.setBorderTop(CellStyle.BORDER_MEDIUM);
		tableHeaderStyle.setBorderLeft(CellStyle.BORDER_MEDIUM);
		tableHeaderStyle.setBorderRight(CellStyle.BORDER_MEDIUM);
		tableHeaderStyle.setBorderBottom(CellStyle.BORDER_MEDIUM);
	}
}
