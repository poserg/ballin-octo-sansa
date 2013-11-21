/**
 * 
 */
package ru.it.rpgu.web.view;

import com.vaadin.ui.Component;

/**
 * @author Sergey Popov (sergey_popov@relex.ru)
 *
 */
public class ReportFormController {
	final ReportForm view;

	public ReportFormController() {
		view = new ReportForm();
		setHandlers();
	}
	
	private void setHandlers() {
	}

	public Component getView() {
		return view;
	}
}
