package ru.it.rpgu.web.statisticalreport.table.strategies;

import ru.it.rpgu.web.statisticalreport.table.ITableView;
import ru.it.rpgu.web.statisticalreport.table.TableModelAgregator;

/**
 * @author Sergey Popov
 *
 */
public interface ITableStrategy {

	void setMainColumnTitle(ITableView tableView, TableModelAgregator tableModel);
}
