package ru.it.rpgu.web.filter.strategies;

import ru.it.rpgu.web.filter.FilterController.IFilterView;
import ru.it.rpgu.web.filter.FilterState;

/**
 * @author Sergey Popov
 *
 */
public class ServiceFilterStrategy implements IFilterStrategy {

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
}
