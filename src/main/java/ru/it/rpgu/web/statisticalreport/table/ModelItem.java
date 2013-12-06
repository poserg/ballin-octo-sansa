package ru.it.rpgu.web.statisticalreport.table;

/**
 * @author Sergey Popov
 *
 */
public class ModelItem {
	private final TableModel tableModel;
	private final String tableName;
	private final String totalName;

	public ModelItem(TableModel tableModel, String tableName, String totalName) {
		this.tableModel = tableModel;
		this.tableName = tableName;
		this.totalName = totalName;
	}

	/**
	 * @return the tableModel
	 */
	public TableModel getTableModel() {
		return tableModel;
	}

	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @return the totalName
	 */
	public String getTotalName() {
		return totalName;
	}
}
