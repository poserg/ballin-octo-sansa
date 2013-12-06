package ru.it.rpgu.web.statisticalreport.table;

/**
 * @author Sergey Popov
 *
 */
public interface ITableView {

	void refresh();

	void addColumn(String columnName);

}
