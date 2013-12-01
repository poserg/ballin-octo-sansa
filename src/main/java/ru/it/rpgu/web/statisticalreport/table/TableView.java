package ru.it.rpgu.web.statisticalreport.table;

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
	}
	
	public Component getView() {
		return table;
	}
	
	public void refresh() {
		removeAllColumns();
		//table.removeAllItems();
		table.addContainerProperty(OFFICE_NAME, String.class, null);
		table.setFooterVisible(true);
		table.setColumnFooter(OFFICE_NAME, TOTAL);
	}

	/**
	 * 
	 */
	private void removeAllColumns() {
		for (Object col : table.getContainerPropertyIds().toArray()) {
			table.removeContainerProperty(col);
		}
	}
	
	public void addColumn(String name, Class<?> type) {
		table.addContainerProperty(name, type, null);
	}
	
	public void addStringColumn(String name) {
		addColumn(name, String.class);
	}
	
	public void addItem(Object[] cells, Object itemId) {
		table.addItem(cells, itemId);
	}
}
