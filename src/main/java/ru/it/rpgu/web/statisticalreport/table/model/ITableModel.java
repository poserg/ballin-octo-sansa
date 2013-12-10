package ru.it.rpgu.web.statisticalreport.table.model;

import ru.it.rpgu.web.statisticalreport.table.view.ITableView;


public interface ITableModel {

	public abstract void drawRows(ITableView tableView);

	public abstract void setFooter();

}