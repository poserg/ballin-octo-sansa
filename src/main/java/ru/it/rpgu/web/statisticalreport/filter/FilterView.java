package ru.it.rpgu.web.statisticalreport.filter;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import ru.it.rpgu.web.statisticalreport.filter.FilterController.IFilterView;
import ru.it.rpgu.web.statisticalreport.filter.strategies.ReportTypeEnum;
import ru.it.rpgu.web.statisticalreport.filter.view.AddParameterWidget;
import ru.it.rpgu.web.statisticalreport.filter.view.DateIntervalWidget;
import ru.it.rpgu.web.statisticalreport.filter.view.DetailStatusPanel;
import ru.it.rpgu.web.statisticalreport.filter.view.ServiceTypeWidget;
import ru.it.rpgu.web.statisticalreport.table.strategies.StatusValue;

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
class FilterView extends VerticalLayout implements IFilterView {

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
	DateIntervalWidget dateInterval;
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

		dateInterval = new DateIntervalWidget();
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

	@Override
	public Date getFromDate() {
		return dateInterval.getFromDate();
	}

	@Override
	public Date getToDate() {
		return dateInterval.getToDate();
	}

	@Override
	public Set<StatusValue> getCheckedStatuses() {
		return detailStatusPanel.getStatuses();
	}

	@Override
	public ServiceType getServiceType() {
		return serviceTypeWidget.getValue();
	}

	@Override
	public boolean getServiceCategory() {
		return addParameterWidget.getServiceCategoryValue();
	}

	@Override
	public boolean getLifeSituation() {
		return addParameterWidget.getLifeSituationValue();
	}
}
