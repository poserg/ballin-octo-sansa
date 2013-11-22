package ru.it.rpgu.web.view.filter.view;

import java.util.Date;

import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;

/**
 * @author Sergey Popov (sergey_popov@relex.ru)
 *
 */
class DateIntervalView extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1183107490041837790L;
	DateField fromDate;
	DateField toDate;

	public DateIntervalView() {
		super();
		HorizontalLayout buildMainLayout = buildMainLayout();
		setContent(buildMainLayout);
	}

	private HorizontalLayout buildMainLayout() {
		HorizontalLayout mainLayout = new HorizontalLayout();
		fromDate = new DateField("С");
		mainLayout.addComponent(fromDate);

		toDate = new DateField("по");
		mainLayout.addComponent(toDate);
		
		return mainLayout;
	}
	
	public Date getFromDate() {
		return fromDate.getValue();
	}
	
	public Date getToDate() {
		return toDate.getValue();
	}
	
	public void addFromDateChangeListener(ValueChangeListener listener) {
		fromDate.addValueChangeListener(listener);
	}
	
	public void addToDateChangeListener(ValueChangeListener listener) {
		toDate.addValueChangeListener(listener);
	}
}
