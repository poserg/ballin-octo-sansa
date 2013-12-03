package ru.it.rpgu.web.statisticalreport.table;


import java.util.ArrayList;
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
	private Boolean isMunicipal = false;
	
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
	 * @param isMunicipal 
	 * @param isRegional
	 */
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
					Object[] row = new Object[colSize + shift];
					row[0] = report.getName();
					// System.out.println("name = " + report.getName());
					row[1] = totalCol[i].toString();
					// System.out.println("total = " + totalCol[i]);
					for (int j = 0; j < colSize; j++) {
						row[j + shift] = report.getApplicationStates().get(j)
								.getApplicationCount().toString();
						// System.out.println("applicationCount = " + row[j + shift]);
					}
					tableView.addItem(row);
				}
				
				if (!isMunicipal && !isRegional){
					List<ApplicationState> applicationStates = dataList.get(0).getApplicationStates();
					for (int j = 0; j < colSize; j++) {
						tableView.setFooter(applicationStates.get(j).getStateName(), totalRow[j].toString());
					}
					tableView.setFooter("Всего заявок", total.toString());
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
					Object[] row = new Object[colSize + shift];
					row[0] = report.getName();
					row[1] = report.getApplicationCount().toString();
					
					tableView.addItem(row);
					
					total += report.getApplicationCount();
				}
				if (!isMunicipal && !isRegional) {
					tableView.setFooter("Всего заявок", total.toString());
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
			Object[] row = new Object[colSize + shift];
			
			if (isMunicipal) {
				row[0] = "Муниципальные услуги";
			} else if (isRegional) {
				row[0] = "Региональные услуги";
			}
			
			for (int i = 1; i < colSize + shift; i++)
				row[i] = "";
			tableView.addItem(row);
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
			tableView.refresh();

			if (serviceCategory != null && serviceCategory) {
				tableView.addStringColumn(CATEGORY_OF_SERVICE);
			}

			if (lifeSituation != null && lifeSituation) {
				tableView.addStringColumn(LIFE_SITUATION);
			}

			tableView.addStringColumn(TOTAL_APPLICATIONS);

			if (list != null) {
				for (ApplicationState statusValue : list) {
					tableView.addStringColumn(statusValue.getStateName());
				}
			}
		}
	}
}
