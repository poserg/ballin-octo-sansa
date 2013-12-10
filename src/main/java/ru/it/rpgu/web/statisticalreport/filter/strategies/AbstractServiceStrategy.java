package ru.it.rpgu.web.statisticalreport.filter.strategies;

import java.util.List;

import ru.it.rpgu.core.model.statisticalreport.Report;
import ru.it.rpgu.core.model.statisticalreport.ReportFilterStateModel;
import ru.it.rpgu.web.statisticalreport.ReportConstants;
import ru.it.rpgu.web.statisticalreport.table.ITableController;
import ru.it.rpgu.web.statisticalreport.table.model.TableModelAgregator;

abstract class AbstractServiceStrategy {

	/**
	 * @param searchParam
	 * @param tableController
	 * @param isMunicipal
	 * @param isRegional
	 */
	protected void createTableModel(ReportFilterStateModel searchParam, ITableController tableController, List<Report> municipalReportList,
			List<Report> regionalReportList) {
				TableModelAgregator tableModel = new TableModelAgregator(ReportConstants.SERVICE);
			
				if (municipalReportList != null) {
					tableModel.addModel(municipalReportList, ReportConstants.MUNICIPAL_SERVICE, ReportConstants.TOTAL_MUNICIPAL);
				}
				
				if (regionalReportList != null) {
					tableModel.addModel(regionalReportList, ReportConstants.REGIONAL_SERVICE, ReportConstants.TOTAL_REGIONAL);
				}
			
				Boolean lifeSituation = searchParam.getLifeSituation();
				if (lifeSituation != null && lifeSituation)
					tableModel.setLifeSituation();
				
				Boolean serviceCategory = searchParam.getServiceCategory();
				if (serviceCategory != null && serviceCategory)
					tableModel.setCategory();
				
				tableController.setData(tableModel);
			}

}
