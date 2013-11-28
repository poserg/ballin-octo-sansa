package ru.it.rpgu.web.statisticalreport.filter.strategies;

import ru.it.rpgu.web.statisticalreport.filter.FilterState;
import ru.it.rpgu.web.statisticalreport.filter.FilterController.IFilterView;


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
