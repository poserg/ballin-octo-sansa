/**
 * 
 */
package ru.it.rpgu.web.view;

import ru.it.rpgu.web.view.filter.FilterController;

import com.vaadin.ui.Component;

/**
 * @author Sergey Popov (sergey_popov@relex.ru)
 *
 */
public class ReportFormController {
	final ReportForm view;

	public ReportFormController() {
		FilterController filterController = new FilterController();
		view = new ReportForm(filterController.getView());
		setHandlers();
	}
	
	private void setHandlers() {
	}

	public Component getView() {
		return view;
	}
}
