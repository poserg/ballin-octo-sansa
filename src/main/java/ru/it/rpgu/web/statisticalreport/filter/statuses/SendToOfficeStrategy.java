package ru.it.rpgu.web.statisticalreport.filter.statuses;

import ru.it.rpgu.core.model.statisticalreport.ReportFilterStateModel;

/**
 * @author Sergey Popov
 *
 */
class SendToOfficeStrategy implements ITableColStrategy {

	@Override
	public void setCheck(ReportFilterStateModel searchParam) {
		searchParam.setSendToOffice(true);
	}
}
