package ru.it.rpgu.web.statisticalreport.table;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.it.rpgu.core.model.statisticalreport.ApplicationState;
import ru.it.rpgu.core.model.statisticalreport.Report;
import ru.it.rpgu.web.statisticalreport.ReportConstants;
import ru.it.rpgu.web.statisticalreport.filter.FilterState;
import ru.it.rpgu.web.statisticalreport.table.model.ModelItem;
import ru.it.rpgu.web.statisticalreport.table.model.TableModel;
import ru.it.rpgu.web.statisticalreport.table.model.TableModelAgregator;
import ru.it.rpgu.web.statisticalreport.table.view.ITableView;
import ru.it.rpgu.web.statisticalreport.table.view.TableView;
import ru.it.rpgu.web.statisticalreport.table.view.XlsView;

import com.vaadin.ui.Component;


/**
 * @author Sergey Popov
 *
 */
public class TableController implements ITableController {
	
	private static final int TITLE_COLUMN_WIDTH = 250;
	private static final int TITLE_COLUMN_WIDTH_BIG = 500;
	private final TableView tableView;
	private TableModelAgregator tableModel;
	private Date fromDate;
	private Date toDate;
	
	public TableController() {
		tableView = new TableView();
	}

	public Component getView() {
		return tableView.getView();
	}

	@Override
	public void setData(TableModelAgregator tableModel) {
		 this.tableModel = tableModel;
		
		generateTable(tableModel, tableView);
	}
	
	public byte[] createXlsFile(String reportName) {
		XlsView xlsView = new XlsView(reportName, fromDate, toDate);
		generateTable(tableModel, xlsView);
		return xlsView.getFile();
	}

	private void generateTable(TableModelAgregator tableModel, ITableView tableView) {
		setColumns(tableView, tableModel.getMainColumnTitle(), tableModel.getStatusColumns(), tableModel.getCategory(), tableModel.getLifeSituation());

		boolean isManyModels = tableModel.getModels().size() > 1;
		int colCount = 0;
		for (ModelItem modelItem : tableModel.getModels()) {
			if (isManyModels) {
				tableView.addItem(modelItem.getTableName());
			}
			
			TableModel table = modelItem.getTableModel();
			
			if (colCount == 0)
				colCount = getColumnCount(tableModel, table);
			
			for (int i = 0; i < table.getReportList().size(); i++) {
				Report report = table.getReportList().get(i);

				int curCol = 0;
				
				String[] row = new String[colCount];
				row[curCol++] = report.getName();
				
				if (tableModel.getCategory()) {
					String category = report.getApplicationStates().get(0).getCategory();
					row[curCol++] = category != null ? category : ReportConstants.EMPTY_STRING; //:TODO
				}
				
				if (tableModel.getLifeSituation()) {
					String lifeSituation = report.getApplicationStates().get(0).getLifeSituation();
					row[curCol++] = lifeSituation != null ? lifeSituation : ReportConstants.EMPTY_STRING; //:TODO
				}
				
				//Всего
				row[curCol++] = table.getTotalCountByStatuses().get(i).toString();
				
				for (int j = 0; j < table.getTotalRow().size(); j++) { 
					ApplicationState applicationState = report.getApplicationStates().get(j);
					row[curCol++] = applicationState.getApplicationCount().toString();
				}
				
				tableView.addItem(row);
			}
			
			List<Object> row = createRow(tableModel.getCategory(),
					tableModel.getLifeSituation(),
					modelItem.getTotalName(), table.getAllTotal(), table.getStringTotalRow());
			if (isManyModels)
				tableView.addItem(row);
			else 
				tableView.setFooter(row);
		}
		
		//Footer
		if (isManyModels) {
			List<Object> row;
			row = createRow(tableModel.getCategory(),
					tableModel.getLifeSituation(),
					ReportConstants.TOTAL, tableModel.getAllTotal(), tableModel.getTotalRow());
			tableView.setFooter(row);
		}
	}

	private List<Object> createRow(Boolean isCategory, Boolean isLifeSituation,
			String title, Integer allTotal, List<String> rowList) {
		List<Object> row = new ArrayList<Object>();
		row.add(title);

		if (isCategory)
			row.add(ReportConstants.EMPTY_STRING);
		
		if (isLifeSituation)
			row.add(ReportConstants.EMPTY_STRING);
			
		row.add(allTotal.toString());
		
		row.addAll(rowList);
		return row;
	}
	
	/**
	 * @param tableModel
	 * @param table
	 * @return 
	 */
	private int getColumnCount(TableModelAgregator tableModel, TableModel table) {
		int colCount = table.getTotalRow().size();
		
		// Учитываем Название и Всего
		colCount+=2;
		if (tableModel.getCategory())
			colCount++;
		if (tableModel.getLifeSituation())
			colCount++;
		
		return colCount;
	}

	private void setColumns(ITableView tableView, String mainColumnTitle, List<String> statusColumnNames,
			Boolean serviceCategory, Boolean lifeSituation) {

		int tableCaptionCount = getTableCaptionCount(serviceCategory, lifeSituation);
		tableView.setTableCaptionCount(tableCaptionCount);
		tableView.refresh();

		int firstColWidth = statusColumnNames.size() == 0 ? TITLE_COLUMN_WIDTH_BIG : TITLE_COLUMN_WIDTH;
		tableView.addColumn(mainColumnTitle, firstColWidth);
		
		if (serviceCategory != null && serviceCategory) {
			tableView.addColumn(ReportConstants.CATEGORY_OF_SERVICE);
		}

		if (lifeSituation != null && lifeSituation) {
			tableView.addColumn(ReportConstants.LIFE_SITUATION);
		}

		tableView.addColumn(ReportConstants.TOTAL_APPLICATIONS);

		for (String columnName : statusColumnNames) {
			tableView.addColumn(columnName);
		}
		
	}
	
	private int getTableCaptionCount(Boolean serviceCategory, Boolean lifeSituation) {
		int tableCaptionCount = 1;
		if (serviceCategory != null && serviceCategory) {
			tableCaptionCount++;
		}

		if (lifeSituation != null && lifeSituation) {
			tableCaptionCount++;
		}
		
		return tableCaptionCount;
	}
	
	public void setCurrentFilterState(FilterState filterState) {
		toDate = filterState.getToDate();
		fromDate = filterState.getFromDate();
	}
}
