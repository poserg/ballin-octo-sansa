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
class ServiceFilterStrategy implements IFilterStrategy {

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
		
		if (isMunicipal) {
			searchParam.setIsMunicipal(true);
			searchParam.setIsRegional(false);
			List<Report> reportList = StatisticalReportDAO.getServicesReport(searchParam);
			tableController.setData(currentFilterState.getCheckedStatuses(), currentFilterState.getServiceCategory(), currentFilterState.getLifeSituation(), reportList, isMunicipal, false);
		}
		
		if (isRegional) {
			searchParam.setIsMunicipal(false);
			searchParam.setIsRegional(true);
			List<Report> reportList = StatisticalReportDAO.getServicesReport(searchParam);
			tableController.setData(currentFilterState.getCheckedStatuses(), currentFilterState.getServiceCategory(), currentFilterState.getLifeSituation(), reportList, false, isRegional);
		}

	}
}
