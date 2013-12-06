package ru.it.rpgu.web.statisticalreport.table;

import java.util.List;

import ru.it.rpgu.web.statisticalreport.ReportConstants;

import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;

/**
 * @author Sergey Popov
 *
 */
class TableView implements ITableView {

	private final HorizontalLayout mainLayout;
	private Table table;
	private int columnsCount = 0;
	
	public TableView() {
		mainLayout = new HorizontalLayout();
	}
	
	public Component getView() {
		return mainLayout;
	}
	
	@Override
	public void refresh() {
		mainLayout.removeAllComponents();
		table = new Table();
		mainLayout.addComponent(table);
		table.setFooterVisible(true);
		columnsCount = 0;
	}

	public void addColumn(String name, Class<?> type) {
		table.addContainerProperty(name, type, null);
		columnsCount++;
	}

	@Override
	public void addColumn(String name) {
		addColumn(name, String.class);
	}
	
	public void addItem(Object[] cells) {
		if (cells.length < columnsCount) {
			// Если в массиве не достаточное количество элементов
			Object[] fullCells = new Object[columnsCount];
			for (int i = 0; i < cells.length; i++)
				fullCells[i] = cells[i];
			
			for (int i = cells.length; i < columnsCount; i++)
				fullCells[i] = ReportConstants.EMPTY_STRING;
			
			addItem(fullCells);
		} else {
			addItemToTable(cells);
		}
	}
	
	public void addItem(String cell) {
		Object[] cells = new Object[1];
		cells[0] = cell;
		addItem(cells);
	}
	
	public void addItem(List<Object> list) {
		Object[] cells = new Object[list.size()];
		for (int i = 0; i < list.size(); i++) {
			cells[i] = list.get(i);
		}
		addItem(cells);
	}

	/**
	 * @param cells
	 */
	private void addItemToTable(Object[] cells) {
		table.addItem(cells, null);
	}

	public void setFooter(String string, String string2) {
		table.setColumnFooter(string, string2);
	}

	public void setFooter(List<Object> row) {
		// TODO Auto-generated method stub
		
	}
}
