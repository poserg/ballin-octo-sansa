package ru.it.rpgu.web.statisticalreport.table;

import java.util.ArrayList;
import java.util.List;

import ru.it.rpgu.web.statisticalreport.ReportConstants;

import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Component;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.Align;

/**
 * @author Sergey Popov
 *
 */
class TableView implements ITableView {

	private final Panel mainLayout;
	private Table table;
	private int columnsCount = 0;
	private List<String> columnNames;
	private int tableCaption;
	
	public TableView() {
		mainLayout = new Panel();
		mainLayout.setWidth(1090, Unit.PIXELS);
		refresh();
	}
	
	public Component getView() {
		return mainLayout;
	}
	
	@Override
	public void refresh() {
		table = new Table();
		// table.setSizeUndefined();
		mainLayout.setContent(table);
		table.setFooterVisible(true);
		// table.setSizeFull();
		table.setSortEnabled(false);
		columnsCount = 0;
		columnNames = new ArrayList<String>();
	}

	public void addColumn(String name, Class<?> type) {
		table.addContainerProperty(name, type, null);
		columnsCount++;
		
		Align colAlign = columnsCount > tableCaption ? Align.CENTER : Align.LEFT;
		table.setColumnAlignment(name, colAlign);
		columnNames.add(name);
	}

	@Override
	public void addColumn(String name) {
		addColumn(name, String.class);
	}
	
	@Override
	public void addColumn(String columnName, int width) {
		addColumn(columnName);
		table.setColumnWidth(columnName, width);
	}
	
	@Override
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
	
	@Override
	public void addItem(String cell) {
		Object[] cells = new Object[1];
		cells[0] = cell.toUpperCase();
		addItem(cells);
	}
	
	@Override
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
//		TextArea[] row = new TextArea[cells.length];
//		for (int i = 0; i < cells.length; i++) {
//			TextArea cell = new TextArea();
//			cell.setValue(cells[i].toString());
//			cell.setWordwrap(true);
//			cell.setReadOnly(true);
//			cell.setSizeFull();
//			
//			row[i] = cell;
//		}
//		table.addItem(row, null);
		table.addItem(cells, null);
	}

	public void setFooter(String string, String string2) {
		table.setColumnFooter(string, string2);
	}

	@Override
	public void setFooter(List<Object> row) {
		for (int i = 0; i < row.size(); i++) {
			setFooter(columnNames.get(i), row.get(i).toString());
		}
	}

	@Override
	public void setTableCaptionCount(int tableCaption) {
		this.tableCaption = tableCaption;
	}
}
