package ru.it.rpgu.web.statisticalreport.table;

import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;

/**
 * @author Sergey Popov
 *
 */
class TableView {

	private static final String OFFICE_NAME = "Наименование ведомства";
	private static final String TOTAL = "Итого";
	private final HorizontalLayout mainLayout;
	private Table table;
	
	public TableView() {
	        mainLayout = new HorizontalLayout();
	}
	
	public Component getView() {
		return mainLayout;
	}
	
	public void refresh() {
	        mainLayout.removeAllComponents();
	        table = new Table();
	        mainLayout.addComponent(table);
		//table.removeAllItems();
		table.addContainerProperty(OFFICE_NAME, String.class, null);
		table.setFooterVisible(true);
		table.setColumnFooter(OFFICE_NAME, TOTAL);
	}

	public void addColumn(String name, Class<?> type) {
		table.addContainerProperty(name, type, null);
	}
	
	public void addStringColumn(String name) {
		addColumn(name, String.class);
	}
	
	public void addItem(Object[] cells) {
		table.addItem(cells, null);
	}

	public void setFooter(String string, String string2) {
		table.setColumnFooter(string, string2);
	}
}
