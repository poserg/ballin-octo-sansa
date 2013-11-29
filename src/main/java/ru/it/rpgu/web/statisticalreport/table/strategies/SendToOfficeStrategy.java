package ru.it.rpgu.web.statisticalreport.table.strategies;

import ru.it.rpgu.core.model.statisticalreport.ReportFilterStateModel;
import ru.it.rpgu.web.statisticalreport.model.RowElement;

/**
 * @author Sergey Popov
 *
 */
class SendToOfficeStrategy implements ITableColStrategy {

	/* (non-Javadoc)
	 * @see ru.it.rpgu.web.statisticalreport.table.strategies.ITableColStrategy#getValue()
	 */
	@Override
	public Long getValue(RowElement rowElement) {
		return rowElement.getApplicationCount();
	}

	@Override
	public void setCheck(ReportFilterStateModel searchParam) {
		searchParam.setSendToOffice(true);
	}
}
