package ru.it.rpgu.web.table;


import java.util.Set;

import ru.it.rpgu.web.filter.StatusValue;

import com.vaadin.ui.Component;


/**
 * @author Sergey Popov
 *
 */
public class TableController {
	
	private final TableView tableView;
	
	public TableController() {
		tableView = new TableView();
	}

	public Component getView() {
		return tableView.getView();
	}

	/**
	 * Создание столбцов.
	 * @param checkedStatuses
	 * @param serviceCategory
	 * @param lifeSituation
	 */
	public void setColumns(Set<StatusValue> checkedStatuses,
			Boolean serviceCategory, Boolean lifeSituation) {
		tableView.refresh();
		
		if (serviceCategory != null && serviceCategory) {
			tableView.addStringColumn("Категория услуг");
		}
		
		if (lifeSituation != null && lifeSituation) {
			tableView.addStringColumn("Жизненная ситуация");
		}
		
		tableView.addStringColumn("Всего заявок");
		
		if (checkedStatuses != null) {
			for (StatusValue statusValue : checkedStatuses) {
				tableView.addStringColumn(statusValue.getValue());
			}
		}
	}
}
