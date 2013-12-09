package ru.it.rpgu.web.statisticalreport;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ru.it.rpgu.core.model.statisticalreport.Report;
import ru.it.rpgu.core.model.statisticalreport.ReportFilterStateModel;
import ru.it.rpgu.web.statisticalreport.filter.FilterController;
import ru.it.rpgu.web.statisticalreport.filter.FilterState;
import ru.it.rpgu.web.statisticalreport.table.TableController;
import ru.it.rpgu.web.statisticalreport.table.xls.XlsController;

import com.vaadin.server.FileDownloader;
import com.vaadin.server.StreamResource;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;
import com.vaadin.ui.AbstractComponent;

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
				
				if (validateDates(currentFilterState.getFromDate(),
						currentFilterState.getToDate())) {

					ReportFilterStateModel searchParam = FilterState.toSearchParam(currentFilterState);
					
					filterController.getCurrentFilterStrategy().getReport(searchParam, currentFilterState, tableController);
				}
			}

		});

		AbstractComponent topExportToExcellButton = view.getExportToExcelTopButton();
		AbstractComponent bottomExportToExcellButton = view.getExportToExcelBottomButton();
		
		FileDownloader fileDownloaderTop = new FileDownloader(getExcelStream());
		fileDownloaderTop.extend(topExportToExcellButton);

		FileDownloader fileDownloaderBottom = new FileDownloader(getExcelStream());
		fileDownloaderBottom.extend(bottomExportToExcellButton);
		
	}
	

	private StreamResource getExcelStream() {
		SimpleDateFormat formatter = new SimpleDateFormat("yy_MM_dd_");
		StringBuilder sb = new StringBuilder();
		sb.append(formatter.format(new Date()));
		sb.append(filterController.getCurrentFilterStrategy()
				.getReportFileName());
		sb.append(".xls");

		StreamResource resource = new StreamResource(
				new StreamResource.StreamSource() {

					@Override
					public InputStream getStream() {
						byte[] xlsFile = tableController.createXlsFile(
								filterController.getCurrentFilterStrategy()
										.getReportFileName(), filterController
										.getCurrentFilterState().getFromDate(),
								filterController.getCurrentFilterState()
										.getToDate());
						return new ByteArrayInputStream(xlsFile);
					}
				}, sb.toString());
		return resource;
	}
	
	/**
	 * @param currentFilterState
	 * @param report 
	 */
	private void setDataTable(FilterState currentFilterState, List<Report> report) {
		//tableController.setData(
		//		currentFilterState.getCheckedStatuses(),
		//		currentFilterState.getServiceCategory(),
		//		currentFilterState.getLifeSituation(), report);
	}

	private boolean validateDates(Date fromDate, Date toDate) {
		StringBuilder message = new StringBuilder();
		boolean isError = false;
		Date today = new Date();

		if (fromDate != null && fromDate.after(today)) {
			message.append("\nДата начала ещё не наступила.");
			isError = true;
		}

		if (toDate != null && toDate.after(today)) {
			message.append("\nДата завершения ещё не наступила.");
			isError = true;
		}

		if (fromDate != null && toDate != null && fromDate.after(toDate)) {
			message.append("\nДата начала позже даты завершения.");
			isError = true;
		}

		if (isError) {
			Notification.show("Указан не верный диапазон дат",
					message.toString(), Notification.TYPE_WARNING_MESSAGE);
		}

		return !isError;
	}

	public Component getView() {
		return view.getMainLayout();
	}
}
