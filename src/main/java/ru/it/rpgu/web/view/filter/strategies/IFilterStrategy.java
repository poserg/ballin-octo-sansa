package ru.it.rpgu.web.view.filter.strategies;

import ru.it.rpgu.web.view.filter.FilterController.IFilterView;


/**
 * @author Sergey Popov (sergey_popov@relex.ru)
 *
 */
public interface IFilterStrategy {

	/**
	 * Построение формы фильтра.
	 * @return
	 */
	void buildFilterLayout(IFilterView view);
}
