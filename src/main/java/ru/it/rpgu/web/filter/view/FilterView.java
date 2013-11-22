package ru.it.rpgu.web.filter.view;

import java.util.Arrays;
import java.util.List;

import ru.it.rpgu.web.filter.FilterController.IFilterView;
import ru.it.rpgu.web.filter.ReportTypeEnum;

import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Sergey Popov (sergey_popov@relex.ru)
 * 
 */
public class FilterView extends VerticalLayout implements IFilterView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final List<ReportTypeEnum> options = Arrays.asList(ReportTypeEnum.OFFICE,
			ReportTypeEnum.OFFICE_AND_STATUSES, ReportTypeEnum.SERVICE,
			ReportTypeEnum.SERVICE_AND_STATUSES);
	final VerticalLayout rightLayout = new VerticalLayout();
	final VerticalLayout bottomLayout = new VerticalLayout();
	
	NativeSelect select;
	DetailStatusPanel detailStatusPanel;
	ServiceTypeWidget serviceTypeWidget;
	AddParameterWidget addParameterWidget;

	public FilterView() {
		super();

		addComponent(buildLayout());
		addComponent(bottomLayout);
	}

	private HorizontalLayout buildLayout() {
		HorizontalLayout layout = new HorizontalLayout();

		VerticalLayout leftLayout = new VerticalLayout();
		layout.addComponent(leftLayout);

		NativeSelect reportTypeSelect = buildReportTypeSelect();
		leftLayout.addComponent(reportTypeSelect);

		DateIntervalWidget dateInterval = new DateIntervalWidget();
		leftLayout.addComponent(dateInterval.getView());

		layout.addComponent(rightLayout);

		return layout;
	}

	private NativeSelect buildReportTypeSelect() {
		select = new NativeSelect("Выберите отчет", options);
		select.setNullSelectionAllowed(false);
		select.setImmediate(true);
		return select;
	}

	@Override
	public void setReportType(ReportTypeEnum reportType) {
		select.setValue(reportType);
	}

	@Override
	public void setRightLayout(Component component) {
		setComponentToLayout(rightLayout, component);
	}

	/**
	 * @param component
	 */
	private void setComponentToLayout(Layout layout, Component component) {
		layout.removeAllComponents();
		if (component != null)
			layout.addComponent(component);
	}
	
	@Override
	public void setBottomLayout(Component component) {
		setComponentToLayout(bottomLayout, component);
	}

	@Override
	public void setChangeReportTypeListener(ValueChangeListener listener) {
		select.addValueChangeListener(listener);
	}

	@Override
	public Component getDetailStatusPanel() {
		if (detailStatusPanel == null)
			detailStatusPanel = new DetailStatusPanel();
		return detailStatusPanel;
	}

	@Override
	public Component getServiceTypePanel() {
		if (serviceTypeWidget == null)
			serviceTypeWidget = new ServiceTypeWidget();
		return serviceTypeWidget.getView();
	}
	
	@Override
	public Component getAddParameterComponent() {
		if (addParameterWidget == null)
			addParameterWidget = new AddParameterWidget();
		return addParameterWidget.getView();
	}

	@Override
	public ReportTypeEnum getCurrentReportType() {
		return (ReportTypeEnum) select.getValue();
	}

	@Override
	public Component getMainLayout() {
		return this;
	}

	@Override
	public void addComponentToBottomLayout(Component component) {
		bottomLayout.addComponent(component);
	}
}
