package ru.it.rpgu.web.statisticalreport.filter.strategies;

import java.util.List;

import ru.it.rpgu.core.dao.StatisticalReportDAO;
import ru.it.rpgu.core.model.statisticalreport.Report;
import ru.it.rpgu.core.model.statisticalreport.ReportFilterStateModel;
import ru.it.rpgu.web.statisticalreport.ReportConstants;
import ru.it.rpgu.web.statisticalreport.table.ITableController;
import ru.it.rpgu.web.statisticalreport.table.TableModelAgregator;

abstract class AbstractServiceStrategy {

	/**
	 * @param searchParam
	 * @param tableController
	 * @param isMunicipal
	 * @param isRegional
	 */
	protected void createTableModel(ReportFilterStateModel searchParam, ITableController tableController, Boolean isMunicipal,
			Boolean isRegional) {
				TableModelAgregator tableModel = new TableModelAgregator(ReportConstants.SERVICE);
			
				if (isMunicipal) {
					searchParam.setIsMunicipal(true);
					searchParam.setIsRegional(false);
					List<Report> reportList = StatisticalReportDAO.getServicesReport(searchParam);
					tableModel.addModel(reportList, ReportConstants.MUNICIPAL_SERVICE, ReportConstants.TOTAL_MUNICIPAL);
				}
				
				if (isRegional) {
					searchParam.setIsMunicipal(false);
					searchParam.setIsRegional(true);
					List<Report> reportList = StatisticalReportDAO.getServicesReport(searchParam);
					tableModel.addModel(reportList, ReportConstants.REGIONAL_SERVICE, ReportConstants.TOTAL_REGIONAL);
				}
			
				Boolean lifeSituation = searchParam.isLifeSituation();
				if (lifeSituation != null && lifeSituation)
					tableModel.setLifeSituation();
				
				Boolean serviceCategory = searchParam.isServiceCategory();
				if (serviceCategory != null && serviceCategory)
					tableModel.setCategory();
				
				tableController.setData(tableModel);
			}

}