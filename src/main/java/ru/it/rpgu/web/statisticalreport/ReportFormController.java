package ru.it.rpgu.web.statisticalreport;

import ru.it.rpgu.web.statisticalreport.filter.FilterController;
import ru.it.rpgu.web.statisticalreport.filter.FilterState;
import ru.it.rpgu.web.statisticalreport.table.TableController;

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
				tableController.setColumns(
						currentFilterState.getCheckedStatuses(),
						currentFilterState.getServiceCategory(),
						currentFilterState.getLifeSituation());
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

	public Component getView() {
		return view.getMainLayout();
	}
}
