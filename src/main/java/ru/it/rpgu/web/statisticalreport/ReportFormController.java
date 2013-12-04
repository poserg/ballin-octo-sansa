package ru.it.rpgu.web.statisticalreport;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import ru.it.rpgu.core.model.statisticalreport.Report;
import ru.it.rpgu.core.model.statisticalreport.ReportFilterStateModel;
import ru.it.rpgu.web.statisticalreport.filter.FilterController;
import ru.it.rpgu.web.statisticalreport.filter.FilterState;
import ru.it.rpgu.web.statisticalreport.table.TableController;
import ru.it.rpgu.web.statisticalreport.table.XlsController;

import com.vaadin.server.StreamResource;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;

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
		 * Установить слушатель на кнопки Выгрузить в Excel.
		 * @param clickListener
		 */
		void setExportToExcelButtonListener(ClickListener clickListener);
		
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
	}
	
	final IReportForm view;
	final FilterController filterController;
	final TableController tableController;

	public ReportFormController() {
		filterController = new FilterController();
		tableController = new TableController();
		
		view = new ReportForm(new StreamResource.StreamSource() {
			
			@Override
			public InputStream getStream() {
				FilterState currentFilterState = filterController
						.getCurrentFilterState();

				if (validateDates(currentFilterState.getFromDate(),
						currentFilterState.getToDate())) {

					ReportFilterStateModel searchParam = FilterState.toSearchParam(currentFilterState);
					
					XlsController xlsController = new XlsController();
					filterController.getCurrentFilterStrategy().getReport(searchParam, currentFilterState, xlsController);
					
					return new ByteArrayInputStream(xlsController.getFile());
				}

				return null;
			}
		});
		view.setFilterView(filterController.getView());
		view.setTableView(tableController.getView());
		
		setListeners();
	}
	
	private void setListeners() {
		view.setFormButtonListener(new ClickListener() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				// System.out.println("started button click");

				FilterState currentFilterState = filterController
						.getCurrentFilterState();
				
				// System.out.println("State was getted");
				// System.out.println("fromDate = " + currentFilterState.getFromDate());
				// System.out.println("toDate = " + currentFilterState.getToDate());

				if (validateDates(currentFilterState.getFromDate(),
						currentFilterState.getToDate())) {

					ReportFilterStateModel searchParam = FilterState.toSearchParam(currentFilterState);
					
					// System.out.println("started query");
					filterController.getCurrentFilterStrategy().getReport(searchParam, currentFilterState, tableController);
					// System.out.println("size report = " + report != null ? report.size() : "null");
					// System.out.println("set datatable");
					// setDataTable(currentFilterState, report);
				}
			}

		});
		
		view.setExportToExcelButtonListener(new ClickListener() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
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
			message.append("<br/>Дата начала ещё не наступила.");
			isError = true;
		}

		if (toDate != null && toDate.after(today)) {
			message.append("<br/>Дата завершения ещё не наступила.");
			isError = true;
		}

		if (fromDate != null && toDate != null && fromDate.after(toDate)) {
			message.append("<br/>Дата начала позже даты завершения.");
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
