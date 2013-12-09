package ru.it.rpgu.web.statisticalreport.filter.strategies;

import java.util.List;

import ru.it.rpgu.core.dao.StatisticalReportDAO;
import ru.it.rpgu.core.model.statisticalreport.Report;
import ru.it.rpgu.core.model.statisticalreport.ReportFilterStateModel;
import ru.it.rpgu.web.statisticalreport.filter.FilterController.IFilterView;
import ru.it.rpgu.web.statisticalreport.filter.FilterState;
import ru.it.rpgu.web.statisticalreport.table.ITableController;

/**
 * @author Sergey Popov
 *
 */
class OfficeFilterStrategy extends AbstractOfficeStrategy implements IFilterStrategy {

	private static final String REPORT_FILE_NAME = "Отчет_по_заявкам_в_разрезе_ведомств";

	/* (non-Javadoc)
	 * @see ru.it.rpgu.web.view.IFilterStrategy#buildFilterLayout(ru.it.rpgu.web.view.FilterController.IFilterView)
	 */
	@Override
	public void buildFilterLayout(IFilterView view) {
		view.setRightLayout(null);
		view.setBottomLayout(null);
	}

	@Override
	public FilterState getCurrentFilterState(IFilterView view) {
		FilterState filterState = new FilterState();
		filterState.setFromDate(view.getFromDate());
		filterState.setToDate(view.getToDate());
		return filterState;
	}

	@Override
	public void getReport(ReportFilterStateModel searchParam,
			FilterState currentFilterState, ITableController tableController) {
		List<Report> reportList = StatisticalReportDAO.getDepartmentsReport(searchParam);
		createTableModel(tableController, reportList);
	}

	@Override
	public String getReportName() {
		return ReportTypeEnum.OFFICE.toString();
	}

	@Override
	public String getReportFileName() {
		return "office_report";
	}

}
