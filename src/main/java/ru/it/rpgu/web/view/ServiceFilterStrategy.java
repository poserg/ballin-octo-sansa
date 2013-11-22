package ru.it.rpgu.web.view;

import ru.it.rpgu.web.view.FilterController.IFilterView;

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
