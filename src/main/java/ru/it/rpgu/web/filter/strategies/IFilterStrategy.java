package ru.it.rpgu.web.filter.strategies;

import ru.it.rpgu.web.filter.FilterController.IFilterView;
import ru.it.rpgu.web.filter.FilterState;


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

	/**
	 * Получить текущее состояние фильтра.
	 * @param view
	 * @return
	 */
	FilterState getCurrentFilterState(IFilterView view);
}
