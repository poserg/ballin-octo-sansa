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
class ServiceFilterStrategy extends AbstractServiceStrategy implements IFilterStrategy {

	private static final String REPORT_FILE_NAME = "Отчет_по_заявкам_в_разрезе_услуг";

	/* (non-Javadoc)
	 * @see ru.it.rpgu.web.view.IFilterStrategy#buildFilterLayout()
	 */
	@Override
	public void buildFilterLayout(IFilterView view) {
		view.setRightLayout(null);
		view.setBottomLayout(view.getServiceTypePanel());
	}

	@Override
	public FilterState getCurrentFilterState(IFilterView view) {
		FilterState filterState = new FilterState();
		filterState.setFromDate(view.getFromDate());
		filterState.setToDate(view.getToDate());
		filterState.setServiceType(view.getServiceType());
		return filterState;
	}

	@Override
	public void getReport(ReportFilterStateModel searchParam,
			FilterState currentFilterState, ITableController tableController) {
		Boolean isMunicipal = searchParam.getIsMunicipal();
		Boolean isRegional = searchParam.getIsRegional();
		
		List<Report> municipalReportList = null;
		if (isMunicipal) {
			searchParam.setIsMunicipal(true);
			searchParam.setIsRegional(false);
			municipalReportList = StatisticalReportDAO.getServicesReport(searchParam);
		}
		
		List<Report> regionalReportList = null;
		if (isRegional) {
			searchParam.setIsMunicipal(false);
			searchParam.setIsRegional(true);
			regionalReportList = StatisticalReportDAO.getServicesReport(searchParam);
		}
		
		createTableModel(searchParam, tableController, municipalReportList, regionalReportList);
	}

	@Override
	public String getReportName() {
		return ReportTypeEnum.SERVICE.toString();
	}

	@Override
	public String getReportFileName() {
		return "service_report";
	}

	@Override
	public boolean validateFitlerState(FilterState filterState) {
		return FilterStateValidator.validateDates(filterState.getFromDate(), filterState.getToDate());
	}
}
