package ru.it.rpgu.web.statisticalreport.filter.strategies;


import ru.it.rpgu.core.model.statisticalreport.ReportFilterStateModel;
import ru.it.rpgu.web.statisticalreport.filter.FilterController.IFilterView;
import ru.it.rpgu.web.statisticalreport.filter.FilterState;
import ru.it.rpgu.web.statisticalreport.table.ITableController;

/**
 * @author Sergey Popov
 *
 */
class ServiceFilterStrategy extends AbstractServiceStrategy implements IFilterStrategy {

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
		
		createTableModel(searchParam, tableController, isMunicipal, isRegional);
	}
}
