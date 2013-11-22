package ru.it.rpgu.web.filter.strategies;

import ru.it.rpgu.web.filter.FilterController.IFilterView;
import ru.it.rpgu.web.filter.FilterState;

/**
 * @author Sergey Popov
 *
 */
public class OfficeAndStatusesFilterStrategy implements IFilterStrategy {

	/* (non-Javadoc)
	 * @see ru.it.rpgu.web.view.IFilterStrategy#buildFilterLayout(ru.it.rpgu.web.view.FilterController.IFilterView)
	 */
	@Override
	public void buildFilterLayout(IFilterView view) {
		view.setRightLayout(view.getDetailStatusPanel());
		view.setBottomLayout(null);
	}

	@Override
	public FilterState getCurrentFilterState(IFilterView view) {
		FilterState filterState = new FilterState();
		filterState.setFromDate(view.getFromDate());
		filterState.setToDate(view.getToDate());
		filterState.setCheckedStatuses(view.getCheckedStatuses());
		return filterState;
	}

}
