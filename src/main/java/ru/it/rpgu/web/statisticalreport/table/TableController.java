package ru.it.rpgu.web.statisticalreport.table;


import java.util.ArrayList;
import java.util.List;

import ru.it.rpgu.core.model.statisticalreport.ApplicationState;
import ru.it.rpgu.core.model.statisticalreport.Report;
import ru.it.rpgu.web.statisticalreport.ReportConstants;

import com.vaadin.ui.Component;


/**
 * @author Sergey Popov
 *
 */
public class TableController implements ITableController {
	
	private final TableView tableView;
	private TableModelAgregator tableModel;
	
	public TableController() {
		tableView = new TableView();
	}

	public Component getView() {
		return tableView.getView();
	}

	@Override
	public void setData(TableModelAgregator tableModel) {
		 this.tableModel = tableModel;
		
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
				
				if (tableModel.getCategory())
					row[curCol++] = ""; //:TODO
				
				if (tableModel.getLifeSituation())
					row[curCol++] = ""; //:TODO
				
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
					modelItem.getTotalName(), table.getStringTotalRow());
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
					ReportConstants.EMPTY_STRING, tableModel.getTotalRow());
			tableView.setFooter(row);
		}
		
	}

	private List<Object> createRow(Boolean isCategory, Boolean isLifeSituation,
			String title, List<String> rowList) {
		List<Object> row = new ArrayList<Object>();
		row.add(title);

		if (isCategory)
			row.add(ReportConstants.EMPTY_STRING);
		
		if (isLifeSituation)
			row.add(ReportConstants.EMPTY_STRING);
		
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
		tableView.refresh();

		tableView.addColumn(mainColumnTitle);
		
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
}
