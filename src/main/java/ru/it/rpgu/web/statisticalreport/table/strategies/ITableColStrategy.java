package ru.it.rpgu.web.statisticalreport.table.strategies;

import ru.it.rpgu.core.model.statisticalreport.ReportFilterStateModel;
import ru.it.rpgu.web.statisticalreport.model.RowElement;

/**
 * @author Sergey Popov
 *
 */
public interface ITableColStrategy {

	/**
	 * Получить значение поля из строки
	 * @return
	 */
	Long getValue(RowElement rowElement);
	
	
	void setCheck(ReportFilterStateModel searchParam);
}
