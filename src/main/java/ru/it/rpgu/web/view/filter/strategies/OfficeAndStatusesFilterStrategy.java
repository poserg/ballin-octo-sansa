package ru.it.rpgu.web.view.filter.strategies;

import ru.it.rpgu.web.view.filter.FilterController;
import ru.it.rpgu.web.view.filter.FilterController.IFilterView;

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

}
