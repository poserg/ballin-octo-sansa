package ru.it.rpgu.web.statisticalreport.table.strategies;

import ru.it.rpgu.core.model.statisticalreport.ReportFilterStateModel;

/**
 * @author Sergey Popov
 *
 */
public interface ITableColStrategy {

	void setCheck(ReportFilterStateModel searchParam);
}
