package ru.it.rpgu.web.statisticalreport.filter.strategies;

import java.util.List;

import ru.it.rpgu.core.model.statisticalreport.Report;
import ru.it.rpgu.web.statisticalreport.ReportConstants;
import ru.it.rpgu.web.statisticalreport.table.ITableController;
import ru.it.rpgu.web.statisticalreport.table.model.TableModelAgregator;

abstract class AbstractOfficeStrategy {

	protected void createTableModel(ITableController tableController, List<Report> reportList) {
		TableModelAgregator tableModel = new TableModelAgregator(null);
		tableModel.addModel(reportList, ReportConstants.DEPARTMENT, ReportConstants.TOTAL);
		tableController.setData(tableModel);
	}

}