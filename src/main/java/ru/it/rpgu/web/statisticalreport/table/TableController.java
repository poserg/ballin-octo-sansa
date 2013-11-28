package ru.it.rpgu.web.statisticalreport.table;


import java.util.Set;

import ru.it.rpgu.web.statisticalreport.filter.StatusValue;

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
	 */
	public void setColumns(Set<StatusValue> checkedStatuses,
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
