package ru.it.rpgu.web.statisticalreport.table;


import java.util.List;
import java.util.Set;

import ru.it.rpgu.core.model.statisticalreport.ApplicationState;
import ru.it.rpgu.core.model.statisticalreport.Report;
import ru.it.rpgu.web.statisticalreport.table.strategies.StatusValue;

import com.vaadin.ui.Component;


/**
 * @author Sergey Popov
 *
 */
public class TableController {
	
	private static final String TOTAL_APPLICATIONS = "Всего заявок";
	private static final String LIFE_SITUATION = "Жизненная ситуация";
	private static final String CATEGORY_OF_SERVICE = "Категория услуг";
	private final TableView tableView;
	
	public TableController() {
		tableView = new TableView();
	}

	public Component getView() {
		return tableView.getView();
	}

	/**
	 * Создание столбцов.
	 * @param checkedStatuses - выбранные статусы
	 * @param serviceCategory - нужен столбец Категория услуг
	 * @param lifeSituation - нужен столбец Жизненная ситуация
	 * @param dataList 
	 */
	public void setData(Set<StatusValue> checkedStatuses,
			Boolean serviceCategory, Boolean lifeSituation, List<Report> dataList) {
		setColumns(checkedStatuses, serviceCategory, lifeSituation);
		
		
		{
			int rowSize = dataList.size();
			Integer[] totalCol = new Integer[rowSize];
			
			boolean isExistStates = rowSize > 0 && dataList.get(0).getApplicationStates() != null;
			int colSize = 0;
			if (isExistStates)
				colSize = dataList.get(0).getApplicationStates().size();
			// Учитываем столбец Всего
			colSize++;

			Integer[] totalRow;
			totalRow = new Integer[colSize];

			for (int i = 0; i < dataList.size(); i++) {
				Report report = dataList.get(i);
				for (int j = 0; j < report.getApplicationStates().size(); j++) {
					Integer stateCount = report.getApplicationStates().get(0)
							.getApplicationCount();
					totalCol[i] += stateCount;

					if (isExistStates)
						totalRow[j + 1] += stateCount;
				}

				totalRow[0] += totalCol[i];
			}
		}
		
		
		int rowCount = checkedStatuses != null ? checkedStatuses.size() : 0;
		// Добавляются два столбца для Названия и Всего
		rowCount += 2;
		
		Object[] totalRow = new Object[rowCount];
		
		for (Report report : dataList) {
			Object[] row = new String[rowCount];
			
			// Название
			row[0] = report.getDepartmentName();

			int curCol = 2;
			Long total = 0L;
			for (StatusValue statusValue : checkedStatuses) {
				String value = statusValue.getValue();
				Integer count = 0;
				for (ApplicationState applicationState : report.getApplicationStates()) {
					if (applicationState.getStateName().equals(value)) {
						count = applicationState.getApplicationCount();
						break;
					}
				}
				row[curCol] = count;
				curCol++;
				total+=count;
			}
			
			row[1] = total;
			
			tableView.addItem(row, report.getDepartmentId());
		}
	}

	/**
	 * @param checkedStatuses
	 * @param serviceCategory
	 * @param lifeSituation
	 */
	private void setColumns(Set<StatusValue> checkedStatuses,
			Boolean serviceCategory, Boolean lifeSituation) {
		tableView.refresh();
		
		if (serviceCategory != null && serviceCategory) {
			tableView.addStringColumn(CATEGORY_OF_SERVICE);
		}
		
		if (lifeSituation != null && lifeSituation) {
			tableView.addStringColumn(LIFE_SITUATION);
		}
		
		tableView.addStringColumn(TOTAL_APPLICATIONS);
		
		if (checkedStatuses != null) {
			for (StatusValue statusValue : checkedStatuses) {
				tableView.addStringColumn(statusValue.getValue());
			}
		}
	}
}
