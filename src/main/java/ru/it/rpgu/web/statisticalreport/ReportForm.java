package ru.it.rpgu.web.statisticalreport;

import ru.it.rpgu.web.statisticalreport.ReportFormController.IReportForm;

import com.vaadin.server.FileDownloader;
import com.vaadin.server.StreamResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;

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
	
	private final StreamResource.StreamSource source;

	public ReportForm(StreamResource.StreamSource source) {
		this.source = source;
		Component mainLayout = buildMainLayout();
		setCompositionRoot(mainLayout);
	}

	private Component buildMainLayout() {
		FormLayout mainLayout = new FormLayout();
		Panel panel = new Panel(REPORTS);
		
		filterLayout = new HorizontalLayout();
		panel.setContent(filterLayout);
		
		HorizontalLayout buttonLayout = new HorizontalLayout();
		formButton = new Button(FORM);
		topExportToExcellButton = new Button(EXPORT_TO_EXCEL);
		buttonLayout.addComponents(formButton, topExportToExcellButton);

		tableLayout = new HorizontalLayout();
		
		bottomExportToExcellButton = new Button(EXPORT_TO_EXCEL);
		
		mainLayout.addComponents(panel, buttonLayout, tableLayout, bottomExportToExcellButton);
		
		FileDownloader fileDownloader = new FileDownloader(getExcelStream());
		// fileDownloader.extend(bottomExportToExcellButton);
		fileDownloader.extend(topExportToExcellButton);
		
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

	private StreamResource getExcelStream() {
		StreamResource resource = new StreamResource ( source, "report.xls");
		return resource;
	}
}
