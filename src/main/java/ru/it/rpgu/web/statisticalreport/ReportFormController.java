package ru.it.rpgu.web.statisticalreport;

import java.util.Date;
import java.util.List;

import ru.it.rpgu.core.model.statisticalreport.Report;
import ru.it.rpgu.core.model.statisticalreport.ReportFilterStateModel;
import ru.it.rpgu.web.statisticalreport.filter.FilterController;
import ru.it.rpgu.web.statisticalreport.filter.FilterState;
import ru.it.rpgu.web.statisticalreport.table.TableController;

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
					
					List<Report> report = filterController.getCurrentFilterStrategy().getReport(searchParam);
					
					setDataTable(currentFilterState, report);
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
		tableController.setData(
				currentFilterState.getCheckedStatuses(),
				currentFilterState.getServiceCategory(),
				currentFilterState.getLifeSituation(), report);
	}

	private boolean validateDates(Date fromDate, Date toDate) {
		if (fromDate == null || toDate == null) {
			return false;
		}
		StringBuilder message = new StringBuilder();
		boolean isError = false;
		Date today = new Date();

		if (fromDate.after(today)) {
			message.append("<br/>Дата начала ещё не наступила.");
			isError = true;
		}

		if (toDate.after(today)) {
			message.append("<br/>Дата завершения ещё не наступила.");
			isError = true;
		}

		if (fromDate.after(toDate)) {
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
