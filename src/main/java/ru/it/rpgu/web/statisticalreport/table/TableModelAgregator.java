package ru.it.rpgu.web.statisticalreport.table;

import java.util.ArrayList;
import java.util.List;

import ru.it.rpgu.core.model.statisticalreport.Report;

/**
 * @author Sergey Popov
 *
 */
public class TableModelAgregator implements ITableModel {
	private final List<ModelItem> models = new ArrayList<TableModelAgregator.ModelItem>();
	
	public void addModel(List<Report> reportList, String tableName, String totalName) {
		TableModel tableModel = new TableModel(reportList);
		ModelItem modelItem = new ModelItem(tableModel, tableName, totalName);
		models.add(modelItem);
	}
	
	/* (non-Javadoc)
	 * @see ru.it.rpgu.web.statisticalreport.table.ITabeleModel#drawRows(ru.it.rpgu.web.statisticalreport.table.ITableView)
	 */
	@Override
	public void drawRows(ITableView tableView) {
		for (ModelItem model : models) {
			// model.getTableModel()
			List<String> row = new ArrayList<String>();
		}
	}
	
	/* (non-Javadoc)
	 * @see ru.it.rpgu.web.statisticalreport.table.ITabeleModel#setFooter()
	 */
	@Override
	public void setFooter() {
		
	}
	
	private class ModelItem {
		
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
}
