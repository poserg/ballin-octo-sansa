package ru.it.rpgu.web.statisticalreport.table;

import java.util.ArrayList;
import java.util.List;

import ru.it.rpgu.core.model.statisticalreport.ApplicationState;
import ru.it.rpgu.core.model.statisticalreport.Report;

/**
 * @author Sergey Popov
 *
 */
public class TableModel implements ITableModel {

	private final List<Report> reportList;
	private final List<Integer> totalCountByStatuses;
	private final List<Integer> totalRow;
	private final Integer allTotal;

	public TableModel(List<Report> reportList) {
		this.reportList = reportList;
		totalCountByStatuses = getTotalCountByStatuses(reportList);
		totalRow = calcTotalRow(reportList);
		allTotal = calcAllTotal(totalRow);
	}

	private Integer calcAllTotal(List<Integer> totalRow) {
		Integer result = 0;
		for (Integer integer : totalRow) {
			result += integer;
		}
		return result;
	}

	/**
	 * @param reportList
	 * @return 
	 */
	private List<Integer> getTotalCountByStatuses(List<Report> reportList) {
		List<Integer> result = new ArrayList<Integer>(reportList.size());
		countTotalBytStatuses(result);
		return result;
	}

	/**
	 * @param reportList
	 * @return 
	 */
	private List<Integer> calcTotalRow(List<Report> reportList) {
		int size = 0;
		List<Integer> result;
		if (reportList.size() > 0) {
			List<ApplicationState> applicationStates = reportList.get(0).getApplicationStates();
			if (applicationStates != null)
				size = applicationStates.size();
			result = new ArrayList<Integer>(size);
			initArray(result);
			countTotalRow();
		} else
			result = new ArrayList<Integer>(0);
		
		return result;
	}

	private void initArray(List<Integer> arr) {
		for (int i = 0; i < arr.size(); i++) {
			arr.set(i, 0);
		}
	}

	private void countTotalRow() {
		for (Report report : reportList) {
			for (int i = 0; i < totalRow.size(); i++) {
				// Нужно чтобы статусы приходили в одинаковом порядке!
				ApplicationState state = report.getApplicationStates().get(i);
				totalRow.set(i, totalRow.get(i) + state.getApplicationCount());
			}
		}
	}

	private void countTotalBytStatuses(List<Integer> totalCountByStatuses) {
		for (int i = 0; i < reportList.size(); i++) {
			Report report = reportList.get(i);
			Integer total = 0;
			if (report.getApplicationStates() != null && report.getApplicationStates().size() > 0) {
				for (ApplicationState state : report.getApplicationStates()) {
					total += state.getApplicationCount();
				}
			} else if (report.getApplicationCount() != null) {
				total = report.getApplicationCount();
			}
			
			totalCountByStatuses.set(i, total);
		}
	}

	@Override
	public void drawRows(ITableView tableView) {
		// TODO Auto-generated method stub
		
	}

	// Установка заголовка для подтаблицы
	public void setTitle() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFooter() {
		// TODO Auto-generated method stub
		
	}
}
