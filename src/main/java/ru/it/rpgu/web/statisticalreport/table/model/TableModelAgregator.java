package ru.it.rpgu.web.statisticalreport.table.model;

import java.util.ArrayList;
import java.util.List;

import ru.it.rpgu.core.model.statisticalreport.ApplicationState;
import ru.it.rpgu.core.model.statisticalreport.Report;
import ru.it.rpgu.web.statisticalreport.filter.statuses.StatusValue;

/**
 * @author Sergey Popov
 *
 */
public class TableModelAgregator {
	private final List<ModelItem> models = new ArrayList<ModelItem>();
	
	private final List<String> statusColumnNames;
	private final List<Integer> statusIndexes;
	private Integer allTotal = 0;
	private List<Integer> totalRow;
	
	private Boolean lifeSituation = false;
	private Boolean category = false;

	private final String mainColumnTitle;
	
	public TableModelAgregator(String mainColumnTitle, List<StatusValue> statusList) {
		this.mainColumnTitle = mainColumnTitle;
		int size = statusList == null ? 0 : statusList.size();
		statusColumnNames = new ArrayList<String>(size);
		statusIndexes = new ArrayList<Integer>(size);
		setStatusColumnNames(statusList);
	}
	
	public void addModel(List<Report> reportList, String tableName, String totalName) {
		TableModel tableModel = new TableModel(reportList);
		ModelItem modelItem = new ModelItem(tableModel, tableName, totalName);
		getModels().add(modelItem);
		
		calcTotal(tableModel);
		calcAllTotal(tableModel.getAllTotal());
		createIndexes(reportList);
	}
	
	/**
	 * Создание индексов для доступа к статусам.
	 * @param reportList
	 */
	private void createIndexes(List<Report> reportList) {
		if (getStatusIndexes().size() == 0)
			if (reportList != null && reportList.size() > 0
					&& reportList.get(0).getApplicationStates() != null) {
				List<ApplicationState> applicationStates = reportList.get(0)
						.getApplicationStates();
				for (String colName : statusColumnNames) {
					for (int i = 0; i < applicationStates.size(); i++) {
						ApplicationState state = applicationStates.get(i);
						if (colName.equals(state.getStateName())) {
							getStatusIndexes().add(i);
							break;
						}
					}
				}
			}
	}

	private void setStatusColumnNames(List<StatusValue> statusList) {
	        if (statusList != null) {
        		for (StatusValue statusValue : statusList) {
	        		statusColumnNames.add(statusValue.getValue());
        		}
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

	/**
	 * @return the statusIndexes
	 */
	public List<Integer> getStatusIndexes() {
		return statusIndexes;
	}
}
