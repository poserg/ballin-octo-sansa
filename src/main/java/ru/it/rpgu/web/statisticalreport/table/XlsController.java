package ru.it.rpgu.web.statisticalreport.table;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import ru.it.rpgu.core.model.statisticalreport.ApplicationState;
import ru.it.rpgu.core.model.statisticalreport.Report;
import ru.it.rpgu.web.statisticalreport.table.strategies.StatusValue;

/**
 * @author Sergey Popov
 *
 */
//:TODO Переписать всё!
public class XlsController implements ITableController{

	private static final String TOTAL_APPLICATIONS = "Всего заявок";
	private static final String LIFE_SITUATION = "Жизненная ситуация";
	private static final String CATEGORY_OF_SERVICE = "Категория услуг";
	
	private static final String DEPARTMENT_REPORT = "Отчет по заявкам в разрезе ведомств";

	private Boolean isMunicipal = false;
	
	int shift = 8;
	private final Sheet sheet;
	private final Workbook wb;
	
	public XlsController() {
		wb = new HSSFWorkbook();
		
		sheet = wb.createSheet(DEPARTMENT_REPORT);
		
		Row titleRow = sheet.createRow(1);
		Cell cell = titleRow.createCell(0);
		cell.setCellValue(DEPARTMENT_REPORT);
		
		// Date
		Row dateRow = sheet.createRow(3);
		Cell dateLabelCell = dateRow.createCell(0);
		dateLabelCell.setCellValue("Дата формирования отчета:");
		Cell dateCell = dateRow.createCell(1);
		Date today = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		dateCell.setCellValue(formatter.format(today));
		
		// Date interval
		Row dateIntervalRow = sheet.createRow(5);
		Cell dateIntervalLabel = dateIntervalRow.createCell(0);
		dateIntervalLabel.setCellValue("Период:");
		Cell dateInterval = dateIntervalRow.createCell(1);
		
		SimpleDateFormat formatter2 = new SimpleDateFormat("dd.MM.yyyy");
		String date1 = formatter2.format(today);
		String date2 = formatter2.format(today);
		dateInterval.setCellValue(date1 + "-" + date2);

	}

	@Override
	public void setData(Set<StatusValue> checkedStatuses,
			Boolean serviceCategory, Boolean lifeSituation, List<Report> dataList, Boolean isMunicipal, Boolean isRegional) {
		 // setColumns(checkedStatuses, serviceCategory, lifeSituation);
		this.isMunicipal  = isMunicipal;
		int rowSize = dataList.size();
		Integer[] totalCol = new Integer[rowSize];
		for (int i = 0; i < rowSize; i++)
			totalCol[i] = 0;
		
		boolean isExistStates = rowSize > 0 && dataList.get(0).getApplicationStates() != null;

		setColumns(isExistStates ? dataList.get(0).getApplicationStates() : new ArrayList<ApplicationState>(), serviceCategory, lifeSituation, !(isMunicipal && isRegional));
		
		{
			int colSize = 0;
			if (isExistStates)
				colSize = dataList.get(0).getApplicationStates().size();
			
			// Учитываем Название и Всего
			int shift = 2;
			setMunicipalOrRegional(isMunicipal, isRegional, colSize, shift);

			Integer[] totalRow;
			totalRow = new Integer[colSize];
			for (int i = 0; i < colSize; i++)
				totalRow[i] = 0;

			if (isExistStates) {
				Integer total = 0;

				for (int i = 0; i < rowSize; i++) {
					Report report = dataList.get(i);

					for (int j = 0; j < colSize; j++) {
						Integer stateCount = report.getApplicationStates()
								.get(j).getApplicationCount();
						totalCol[i] += stateCount;
						totalRow[j] += stateCount;
					}

					total += totalCol[i];
				}

				// Формирование строк
				for (int i = 0; i < rowSize; i++) {
					Report report = dataList.get(i);
					Row row = sheet.createRow(sheet.getLastRowNum() + 1);
					row.createCell(0).setCellValue(report.getName());
					// System.out.println("name = " + report.getName());
					row.createCell(1).setCellValue(totalCol[i].toString());
					// System.out.println("total = " + totalCol[i]);
					for (int j = 0; j < colSize; j++) {
//						row[j + shift] = report.getApplicationStates().get(j)
//								.getApplicationCount().toString();
						row.createCell(row.getLastCellNum() + 1).setCellValue(report.getApplicationStates().get(j)
								.getApplicationCount().toString());
						// System.out.println("applicationCount = " + row[j + shift]);
					}
				}
				
				if (!isMunicipal && !isRegional){
					List<ApplicationState> applicationStates = dataList.get(0).getApplicationStates();
					Row footer = sheet.createRow(sheet.getLastRowNum() + 1);
					footer.createCell(0).setCellValue("Итого");
					footer.createCell(footer.getLastCellNum() + 1).setCellValue(total);
					for (int j = 0; j < colSize; j++) {
						footer.createCell(footer.getLastCellNum() + 1).setCellValue(totalRow[j].toString());
					}
				} else {
					Object[] row = new Object[colSize + shift];
					row[0] = isMunicipal ? "Итого по муниципальным услугам" : "Итого по региональным услугам";
					for (int i = 1; i < shift; i++) {
						row[i] = "";
					}
					for (int j = 0; j < colSize; j++) {
						row[j+shift] = totalRow[j].toString();
					}
				}
			} else {
				// Без детализации по статусам
				
				Integer total = 0;
				for (int i = 0; i < rowSize; i++) {
					Report report = dataList.get(i);
					// System.out.println("reportName = " + report.getName());
					// System.out.println("applicationCount = " + report.getApplicationCount());
					Row row = sheet.createRow(sheet.getLastRowNum() + 1);
					row.createCell(0).setCellValue(report.getName());
					row.createCell(1).setCellValue(report.getApplicationCount());
					
					total += report.getApplicationCount();
				}
				if (!isMunicipal && !isRegional) {
					Row footer = sheet.createRow(sheet.getLastRowNum() + 1);
					footer.createCell(0).setCellValue("Итого");
					footer.createCell(footer.getLastCellNum() + 1).setCellValue(total);
				} else {
					Object[] row = new Object[colSize + shift];
					row[0] = isMunicipal ? "Итого по муниципальным услугам" : "Итого по региональным услугам";
					for (int i = 1; i < shift; i++) {
						row[i] = "";
					}
					row[colSize + shift - 1] = total.toString();
				}
			}
			
			if (this.isMunicipal && isRegional)
				this.isMunicipal = false;
		}
		
	}

	/**
	 * @param isMunicipal
	 * @param isRegional
	 * @param colSize
	 * @param shift
	 */
	private void setMunicipalOrRegional(Boolean isMunicipal,
			Boolean isRegional, int colSize, int shift) {
		if (isMunicipal || isRegional) {
			Row row = sheet.createRow(sheet.getLastRowNum() + 1);
			row.createCell(0).setCellValue(isMunicipal ? "Муниципальные услуги" : "Региональные услуги");
		}
	}

	/**
	 * @param list
	 * @param serviceCategory
	 * @param lifeSituation
	 * @param isNeedRefresh 
	 */
	private void setColumns(List<ApplicationState> list,
			Boolean serviceCategory, Boolean lifeSituation, boolean isNeedRefresh) {
		if (isNeedRefresh) {
			Row titleRow = sheet.createRow(shift);
			
			titleRow.createCell(0).setCellValue("Наименование ведомства");
			if (serviceCategory != null && serviceCategory) {
				titleRow.createCell(titleRow.getLastCellNum() + 1).setCellValue(CATEGORY_OF_SERVICE);
			}

			if (lifeSituation != null && lifeSituation) {
				titleRow.createCell(titleRow.getLastCellNum() + 1).setCellValue(LIFE_SITUATION);
			}

			// titleRow.createCell(titleRow.getLastCellNum() + 1).setCellValue("Итого");

			if (list != null) {
				for (ApplicationState statusValue : list) {
					titleRow.createCell(titleRow.getLastCellNum() + 1).setCellValue(statusValue.getStateName());
				}
			}
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
}
