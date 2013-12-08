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
		if (reportList.size() > 0 && reportList.get(0).getApplicationStates() != null && reportList.get(0).getApplicationStates().size() > 0) {
			List<ApplicationState> applicationStates = reportList.get(0).getApplicationStates();
			for (Report report : reportList) {
		                System.out.println("department = " + report.getName());
			        if (report.getApplicationStates() != null) {
			                for (ApplicationState state : report.getApplicationStates()) {
			                        System.out.println("State: " + state.getStateName() + "; count = " + state.getApplicationCount());
                                        }
			        }
			}
			size = applicationStates.size();
        		System.out.println("++++++++++++++++++++");
			System.out.println("size = " + size);
        		System.out.println("++++++++++++++++++++");
			result = new ArrayList<Integer>(size);
			initArray(result, size);
			countTotalRow(result);
		} else
			result = new ArrayList<Integer>(0);
		
		return result;
	}

	public static void initArray(List<Integer> arr) {
		for (int i = 0; i < arr.size(); i++) {
			arr.add(0);
		}
	}

	public static void initArray(List<Integer> arr, int size) {
		for (int i = 0; i < size; i++) {
			arr.add(0);
		}
	}

	private void countTotalRow(List<Integer> result) {
		for (Report report : getReportList()) {
			for (int i = 0; i < report.getApplicationStates().size(); i++) {
				// Нужно чтобы статусы приходили в одинаковом порядке!
				ApplicationState state = report.getApplicationStates().get(i);
                                result.set(i, result.get(i) + state.getApplicationCount());
			}
		}
	}

	private void countTotalBytStatuses(List<Integer> totalCountByStatuses) {
		for (int i = 0; i < getReportList().size(); i++) {
			Report report = getReportList().get(i);
			Integer total = 0;
			if (report.getApplicationStates() != null && report.getApplicationStates().size() > 0) {
				for (ApplicationState state : report.getApplicationStates()) {
					total += state.getApplicationCount();
				}
			} else if (report.getApplicationCount() != null) {
				total = report.getApplicationCount();
			}
			
			totalCountByStatuses.add(total);
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

	/**
	 * @return the totalRow
	 */
	public List<Integer> getTotalRow() {
		return totalRow;
	}
	
	public List<String> getStringTotalRow() {
		List<String> result = new ArrayList<String>();
		for (Integer integer : totalRow)
			result.add(integer.toString());
		return result;
	}

	/**
	 * @return the allTotal
	 */
	public Integer getAllTotal() {
		return allTotal;
	}

	/**
	 * @return the totalCountByStatuses
	 */
	public List<Integer> getTotalCountByStatuses() {
		return totalCountByStatuses;
	}

	/**
	 * @return the reportList
	 */
	public List<Report> getReportList() {
		return reportList;
	}
}
