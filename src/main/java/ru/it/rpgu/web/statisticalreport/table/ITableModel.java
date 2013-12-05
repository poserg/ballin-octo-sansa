package ru.it.rpgu.web.statisticalreport.table;


public interface ITableModel {

	public abstract void drawRows(ITableView tableView);

	public abstract void setFooter();

}