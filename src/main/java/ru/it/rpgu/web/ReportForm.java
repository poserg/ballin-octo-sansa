package ru.it.rpgu.web;

import ru.it.rpgu.web.ReportFormController.IReportForm;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
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
	HorizontalLayout filterLayout, tableLayout;

	public ReportForm() {
		VerticalLayout mainLayout = buildMainLayout();
		setCompositionRoot(mainLayout);
	}

	private VerticalLayout buildMainLayout() {
		VerticalLayout mainLayout = new VerticalLayout();

		Label header = new Label(REPORTS);
		
		filterLayout = new HorizontalLayout();
		
		HorizontalLayout buttonLayout = new HorizontalLayout();
		formButton = new Button(FORM);
		topExportToExcellButton = new Button(EXPORT_TO_EXCEL);
		buttonLayout.addComponents(formButton, topExportToExcellButton);

		tableLayout = new HorizontalLayout();
		
		bottomExportToExcellButton = new Button(EXPORT_TO_EXCEL);
		
		mainLayout.addComponents(header, filterLayout, buttonLayout, tableLayout, bottomExportToExcellButton);
		
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
	
	@Override
	public void setFilterView(Component filterView) {
		setViewToLayout(filterLayout, filterView);
	}

	@Override
	public void setTableView(Component tableView) {
		setViewToLayout(tableLayout, tableView);
	}

	/**
	 * @param view
	 */
	public void setViewToLayout(Layout layout, Component view) {
		layout.removeAllComponents();
		layout.addComponent(view);
	}
}
