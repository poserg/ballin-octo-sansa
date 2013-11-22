package ru.it.rpgu.web.view;

import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Sergey Popov (sergey_popov@relex.ru)
 * 
 */
class ReportForm extends CustomComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4439484301300996076L;

	public ReportForm(Component filterView) {
		VerticalLayout mainLayout = buildMainLayout(filterView);
		setCompositionRoot(mainLayout);
	}

	private VerticalLayout buildMainLayout(Component filterView) {
		VerticalLayout mainLayout = new VerticalLayout();

		Label header = new Label("Отчеты");
		mainLayout.addComponent(header);

		mainLayout.addComponent(filterView);
		
		return mainLayout;
	}

}
