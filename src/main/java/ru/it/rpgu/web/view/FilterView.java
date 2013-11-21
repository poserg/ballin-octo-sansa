package ru.it.rpgu.web.view;

import ru.it.rpgu.web.view.FilterController.IFilterView;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Sergey Popov (sergey_popov@relex.ru)
 * 
 */
public class FilterView extends VerticalLayout implements IFilterView {

	VerticalLayout rightLayout = new VerticalLayout();

	public FilterView() {
		super();

		addComponent(buildLayout());
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
		final NativeSelect select = new NativeSelect("Выберите отчет", options);
		select.setNullSelectionAllowed(false);
		select.setValue(options.get(0));

		select.addValueChangeListener(new ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				select.getValue();
			}
		});
		return select;
	}

	@Override
	public void setRightLayout(Component component) {
		rightLayout.removeAllComponents();
		if (component != null)
			rightLayout.addComponent(component);
	}
}
