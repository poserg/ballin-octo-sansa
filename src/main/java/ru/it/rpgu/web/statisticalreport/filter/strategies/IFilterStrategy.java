package ru.it.rpgu.web.statisticalreport.filter.strategies;

import java.util.List;

import ru.it.rpgu.core.model.statisticalreport.Report;
import ru.it.rpgu.core.model.statisticalreport.ReportFilterStateModel;
import ru.it.rpgu.web.statisticalreport.filter.FilterController.IFilterView;
import ru.it.rpgu.web.statisticalreport.filter.FilterState;


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
	 * @return
	 */
	List<Report> getReport(ReportFilterStateModel searchParam);
}
