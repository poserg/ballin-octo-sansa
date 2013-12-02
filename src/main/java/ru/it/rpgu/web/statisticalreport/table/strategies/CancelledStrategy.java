package ru.it.rpgu.web.statisticalreport.table.strategies;

import ru.it.rpgu.core.model.statisticalreport.ReportFilterStateModel;

/**
 * @author Sergey Popov
 *
 */
class CancelledStrategy implements ITableColStrategy {

	/* (non-Javadoc)
	 * @see ru.it.rpgu.web.statisticalreport.table.strategies.ITableColStrategy#setCheck(ru.it.rpgu.core.model.statisticalreport.ReportFilterStateModel)
	 */
	@Override
	public void setCheck(ReportFilterStateModel searchParam) {
		searchParam.setCancelled(true);
	}

}
