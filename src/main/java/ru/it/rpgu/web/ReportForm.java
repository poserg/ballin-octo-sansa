package ru.it.rpgu.web;

import ru.it.rpgu.web.ReportFormController.IReportForm;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Sergey Popov (sergey_popov@relex.ru)
 * 
 */
class ReportForm extends CustomComponent implements IReportForm {

	private static final String REPORTS = "Отчеты";
	private static final String FORM = "Сформировать";
	private static final String EXPORT_TO_EXCEL = "Выгрузить в Excel";
	/**
	 * 
	 */
	private static final long serialVersionUID = -4439484301300996076L;

	Button formButton, topExportToExcellButton, bottomExportToExcellButton;

	public ReportForm(Component filterView, Component tableView) {
		VerticalLayout mainLayout = buildMainLayout(filterView, tableView);
		setCompositionRoot(mainLayout);
	}

	private VerticalLayout buildMainLayout(Component filterView, Component tableView) {
		VerticalLayout mainLayout = new VerticalLayout();

		Label header = new Label(REPORTS);
		
		HorizontalLayout buttonLayout = new HorizontalLayout();
		formButton = new Button(FORM);
		topExportToExcellButton = new Button(EXPORT_TO_EXCEL);
		buttonLayout.addComponents(formButton, topExportToExcellButton);

		bottomExportToExcellButton = new Button(EXPORT_TO_EXCEL);
		
		mainLayout.addComponents(header, filterView, buttonLayout, tableView, bottomExportToExcellButton);
		
		return mainLayout;
	}

	@Override
	public Component getMainLayout() {
		return this;
	}

	@Override
	public void setFormButtonListener(ClickListener clickListener) {
		formButton.addClickListener(clickListener);
	}

	@Override
	public void setExportToExcelButtonListener(ClickListener clickListener) {
		topExportToExcellButton.addClickListener(clickListener);
		bottomExportToExcellButton.addClickListener(clickListener);
	}

}
