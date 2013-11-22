package ru.it.rpgu.web.table;

import com.vaadin.ui.Component;
import com.vaadin.ui.Table;

/**
 * @author Sergey Popov
 *
 */
class TableView {

	private static final String OFFICE_NAME = "Наименование ведомства";
	private static final String TOTAL = "Итого";
	final Table table;
	
	public TableView() {
		table = new Table();
		table.addContainerProperty(OFFICE_NAME, String.class, null);
		
		table.setFooterVisible(true);
        table.setColumnFooter(OFFICE_NAME, TOTAL);
	}
	
	public Component getView() {
		return table;
	}
}
