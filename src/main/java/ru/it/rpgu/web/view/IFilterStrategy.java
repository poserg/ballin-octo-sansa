package ru.it.rpgu.web.view;

import ru.it.rpgu.web.view.FilterController.IFilterView;

/**
 * @author Sergey Popov (sergey_popov@relex.ru)
 *
 */
interface IFilterStrategy {

	/**
	 * Построение формы фильтра.
	 * @return
	 */
	void buildFilterLayout(IFilterView view);
}
