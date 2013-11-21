package ru.it.rpgu.web.view;

import java.util.Arrays;
import java.util.List;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Sergey Popov (sergey_popov@relex.ru)
 * 
 */
class ReportForm extends CustomComponent {

	List<ReportStatusEnum> options = Arrays.asList(ReportStatusEnum.SERVICE,
			ReportStatusEnum.SERVICE_AND_STATUSES, ReportStatusEnum.OFFICE,
			ReportStatusEnum.OFFICE_AND_STATUSES);

	public ReportForm() {
		VerticalLayout mainLayout = buildMainLayout();
		setCompositionRoot(mainLayout);
	}

	private VerticalLayout buildMainLayout() {
		VerticalLayout mainLayout = new VerticalLayout();

		Label header = new Label("Отчеты");
		mainLayout.addComponent(header);

		HorizontalLayout filterPanel = new HorizontalLayout();
		mainLayout.addComponent(filterPanel);


		DetailStatusPanel rightPanel = new DetailStatusPanel();
		filterPanel.addComponent(rightPanel);

		return mainLayout;
	}

}
