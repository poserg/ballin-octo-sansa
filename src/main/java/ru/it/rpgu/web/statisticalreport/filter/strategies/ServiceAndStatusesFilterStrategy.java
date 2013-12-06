package ru.it.rpgu.web.statisticalreport.filter.strategies;

import ru.it.rpgu.core.model.statisticalreport.ReportFilterStateModel;
import ru.it.rpgu.web.statisticalreport.filter.FilterController.IFilterView;
import ru.it.rpgu.web.statisticalreport.filter.FilterState;
import ru.it.rpgu.web.statisticalreport.table.ITableController;

/**
 * @author Sergey Popov
 *
 */
class ServiceAndStatusesFilterStrategy extends AbstractServiceStrategy implements IFilterStrategy {

	/* (non-Javadoc)
	 * @see ru.it.rpgu.web.view.IFilterStrategy#buildFilterLayout(ru.it.rpgu.web.view.FilterController.IFilterView)
	 */
	@Override
	public void buildFilterLayout(IFilterView view) {
		view.setRightLayout(view.getDetailStatusPanel());
		view.setBottomLayout(view.getServiceTypePanel());
		view.addComponentToBottomLayout(view.getAddParameterComponent());
	}

	@Override
	public FilterState getCurrentFilterState(IFilterView view) {
		FilterState filterState = new FilterState();
		filterState.setFromDate(view.getFromDate());
		filterState.setToDate(view.getToDate());
		filterState.setCheckedStatuses(view.getCheckedStatuses());
		filterState.setServiceType(view.getServiceType());
		filterState.setServiceCategory(view.getServiceCategory());
		filterState.setLifeSituation(view.getLifeSituation());
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
