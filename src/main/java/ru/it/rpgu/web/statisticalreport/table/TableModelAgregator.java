package ru.it.rpgu.web.statisticalreport.table;

import java.util.ArrayList;
import java.util.List;

import ru.it.rpgu.core.model.statisticalreport.ApplicationState;
import ru.it.rpgu.core.model.statisticalreport.Report;

/**
 * @author Sergey Popov
 *
 */
public class TableModelAgregator implements ITableModel {
	private final List<ModelItem> models = new ArrayList<ModelItem>();
	
	private List<String> statusColumnNames;
	private Integer allTotal = 0;
	private List<Integer> totalRow;
	
	private Boolean lifeSituation = false;
	private Boolean category = false;

	private final String mainColumnTitle;
	
	public TableModelAgregator(String mainColumnTitle) {
		this.mainColumnTitle = mainColumnTitle;
	}
	
	public void addModel(List<Report> reportList, String tableName, String totalName) {
		TableModel tableModel = new TableModel(reportList);
		ModelItem modelItem = new ModelItem(tableModel, tableName, totalName);
		getModels().add(modelItem);
		
		calcTotal(tableModel);
		calcAllTotal(tableModel.getAllTotal());
		setStatusColumnNames(reportList);
	}
	
	private void setStatusColumnNames(List<Report> reportList) {
		if (statusColumnNames == null) {
			List<ApplicationState> applicationStates = null;
			if (reportList.size() > 0
					&& reportList.get(0).getApplicationStates() != null
					&& reportList.get(0).getApplicationStates().size() > 0) {
				statusColumnNames = new ArrayList<String>(reportList.size());
				applicationStates = reportList.get(0).getApplicationStates();
				
				for (ApplicationState state : applicationStates) {
				        statusColumnNames.add(state.getStateName());
				}
			} else
				statusColumnNames = new ArrayList<String>(0);
		}
	}

	private void calcAllTotal(Integer allTotal) {
		this.allTotal += allTotal;
	}

	private void calcTotal(TableModel tableModel) {
		List<Integer> modelTotalRow = tableModel.getTotalRow();
		
		if (totalRow == null) {
			totalRow = new ArrayList<Integer>(modelTotalRow.size());
			TableModel.initArray(totalRow, modelTotalRow.size());
		}
		
		for (int i = 0; i < modelTotalRow.size(); i++) {
			Integer modelRow = modelTotalRow.get(i);
			Integer current = totalRow.get(i);
			totalRow.set(i, modelRow + current);
		}
	}

	/* (non-Javadoc)
	 * @see ru.it.rpgu.web.statisticalreport.table.ITabeleModel#drawRows(ru.it.rpgu.web.statisticalreport.table.ITableView)
	 */
	@Override
	public void drawRows(ITableView tableView) {
		for (ModelItem model : getModels()) {
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
	
	public List<String> getStatusColumns() {
		return statusColumnNames;
	}
	
	/**
	 * @return the lifeSituation
	 */
	public Boolean getLifeSituation() {
		return lifeSituation;
	}

	/**
	 * @return the category
	 */
	public Boolean getCategory() {
		return category;
	}
	
	public String getMainColumnTitle() {
		String result = null;
		if (getModels().size() == 1) {
			result = getModels().get(0).getTableName();
		} else {
			result = mainColumnTitle;
		}
		
		return result;
	}

	public void setLifeSituation() {
		this.lifeSituation = true;
	}

	public void setCategory() {
		this.category = true;
	}

	/**
	 * @return the models
	 */
	public List<ModelItem> getModels() {
		return models;
	}
	
	public List<String> getTotalRow() {
		List<String> result = new ArrayList<String>();
		for (Integer integer : totalRow)
			result.add(integer.toString());
		return result;
	}
	
	public Integer getAllTotal() {
	        return allTotal;
	}
}
