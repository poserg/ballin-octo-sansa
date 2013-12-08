package ru.it.rpgu.web.statisticalreport.table;

import java.util.List;

/**
 * @author Sergey Popov
 *
 */
public interface ITableView {

	void refresh();

	void addColumn(String columnName);
	
	void addColumn(String columnName, int width);

	void setFooter(List<Object> row);
	
	void addItem(Object[] cells);
	
	/**
	 * Добавление подзаголовка
	 * @param cell
	 */
	void addItem(String cell);
	
	void addItem(List<Object> list);
}
