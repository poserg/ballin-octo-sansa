package ru.it.rpgu.web.statisticalreport;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import ru.it.rpgu.core.model.statisticalreport.ReportFilterStateModel;
import ru.it.rpgu.web.statisticalreport.OnDemandFileDownloader.OnDemandStreamResource;
import ru.it.rpgu.web.statisticalreport.filter.FilterController;
import ru.it.rpgu.web.statisticalreport.filter.FilterState;
import ru.it.rpgu.web.statisticalreport.filter.strategies.IFilterStrategy;
import ru.it.rpgu.web.statisticalreport.table.TableController;

import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;

/**
 * @author Sergey Popov (sergey_popov@relex.ru)
 *
 */
public class ReportFormController {
	
	public interface IReportForm {
		
		Component getMainLayout();
		
		/**
		 * Установить слушатель на кнопку Сформировать.
		 * @param clickListener
		 */
		void setFormButtonListener(ClickListener clickListener);
		
		/**
		 * Установить представление фильтра.
		 * @param filterView
		 */
		void setFilterView(Component filterView);
		
		/**
		 * Установить представление таблицы.
		 * @param tableView
		 */
		void setTableView(Component tableView);
		
		AbstractComponent getExportToExcelTopButton();

		AbstractComponent getExportToExcelBottomButton();
		
		void showDownloadButtons();
	}
	
	final IReportForm view;
	final FilterController filterController;
	final TableController tableController;

	public ReportFormController() {
		filterController = new FilterController();
		tableController = new TableController();
		
		view = new ReportForm();
		view.setFilterView(filterController.getView());
		view.setTableView(tableController.getView());
		
		setListeners();
	}
	
	private void setListeners() {
		view.setFormButtonListener(new ClickListener() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				FilterState currentFilterState = filterController
						.getCurrentFilterState();
				IFilterStrategy currentFilterStrategy = filterController
						.getCurrentFilterStrategy();
				
				if (currentFilterStrategy.validateFitlerState(currentFilterState)) {
					ReportFilterStateModel searchParam = FilterState.toSearchParam(currentFilterState);
					
					currentFilterStrategy.getReport(searchParam, currentFilterState, tableController);
					view.showDownloadButtons();
				}
			}

		});

		AbstractComponent topExportToExcellButton = view.getExportToExcelTopButton();
		AbstractComponent bottomExportToExcellButton = view.getExportToExcelBottomButton();
		
		OnDemandFileDownloader fileDownloaderTop = new OnDemandFileDownloader(getExcelStreamResource());
		fileDownloaderTop.extend(topExportToExcellButton);

		OnDemandFileDownloader fileDownloaderBottom = new OnDemandFileDownloader(getExcelStreamResource());
		fileDownloaderBottom.extend(bottomExportToExcellButton);
		
	}
	

	private OnDemandStreamResource getExcelStreamResource() {
		return new OnDemandStreamResource() {
			
			private static final long serialVersionUID = 746711523246311020L;

			@Override
			public InputStream getStream() {
				byte[] xlsFile = tableController.createXlsFile(filterController
						.getCurrentFilterStrategy().getReportName());
				return new ByteArrayInputStream(xlsFile);
			}
			
			@Override
			public String getFilename() {
				SimpleDateFormat formatter = new SimpleDateFormat("yy_MM_dd_");
				StringBuilder sb = new StringBuilder();
				sb.append(formatter.format(new Date()));
				sb.append(filterController.getCurrentFilterStrategy()
						.getReportFileName());
				sb.append(".xls");
				
				return sb.toString();
			}
		};
	}

	public Component getView() {
		return view.getMainLayout();
	}
}
