package ru.it.rpgu.web;

import ru.it.rpgu.web.filter.FilterController;
import ru.it.rpgu.web.table.TableController;

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
	}
	
	final IReportForm view;

	public ReportFormController() {
		FilterController filterController = new FilterController();
		TableController tableController = new TableController();
		view = new ReportForm(filterController.getView(), tableController.getView());
		setHandlers();
	}
	
	private void setHandlers() {
	}

	public Component getView() {
		return view.getMainLayout();
	}
}
