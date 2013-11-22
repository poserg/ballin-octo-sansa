package ru.it.rpgu.web;

import ru.it.rpgu.web.filter.FilterController;
import ru.it.rpgu.web.filter.strategies.IFilterStrategy;
import ru.it.rpgu.web.table.TableController;

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
		
		setHandlers();
	}
	
	private void setHandlers() {
		view.setFormButtonListener(new ClickListener() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				IFilterStrategy currentFilterStrategy = filterController.getCurrentFilterStrategy();
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
