package ru.it.rpgu.web.statisticalreport.filter.strategies;

import ru.it.rpgu.core.model.statisticalreport.ReportFilterStateModel;
import ru.it.rpgu.web.statisticalreport.filter.FilterController.IFilterView;
import ru.it.rpgu.web.statisticalreport.filter.FilterState;
import ru.it.rpgu.web.statisticalreport.table.ITableController;


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

	/**
	 * Запрос на получение отчёта.
	 * @param searchParam
	 * @param currentFilterState 
	 * @param tableController
	 */
	void getReport(ReportFilterStateModel searchParam, FilterState currentFilterState, ITableController tableController);
}
