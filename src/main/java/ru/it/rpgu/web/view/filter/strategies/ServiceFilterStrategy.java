package ru.it.rpgu.web.view.filter.strategies;

import ru.it.rpgu.web.view.filter.FilterController;
import ru.it.rpgu.web.view.filter.FilterController.IFilterView;

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

}
