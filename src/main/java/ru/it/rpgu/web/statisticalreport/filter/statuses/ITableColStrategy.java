package ru.it.rpgu.web.statisticalreport.filter.statuses;

import ru.it.rpgu.core.model.statisticalreport.ReportFilterStateModel;

/**
 * @author Sergey Popov
 *
 */
public interface ITableColStrategy {

	void setCheck(ReportFilterStateModel searchParam);
}
