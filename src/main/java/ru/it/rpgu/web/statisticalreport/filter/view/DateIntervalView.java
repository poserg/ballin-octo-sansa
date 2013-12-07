package ru.it.rpgu.web.statisticalreport.filter.view;

import java.util.Date;

import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Sergey Popov
 *
 */
class DateIntervalView extends VerticalLayout {

	private static final String END_DATE = "Дата завершнеия интервала";
	private static final String START_DATE = "Дата начала интервала";
	private static final String DATE_FORMAT = "dd.MM.yyyy";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1183107490041837790L;
	DateField fromDate;
	DateField toDate;

	public DateIntervalView() {
		super();
		Component buildMainLayout = buildMainLayout();
		addComponent(buildMainLayout);
	}

	private Component buildMainLayout() {
		// HorizontalLayout mainLayout = new HorizontalLayout();
		FormLayout mainLayout = new FormLayout();
		fromDate = new DateField("С");
		fromDate.setDateFormat(DATE_FORMAT);
		fromDate.setSizeFull();
		fromDate.setDescription(START_DATE);
		//fromDate.setLenient(true);
		mainLayout.addComponent(fromDate);

		toDate = new DateField("по");
		toDate.setDateFormat(DATE_FORMAT);
		toDate.setDescription(END_DATE);
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
