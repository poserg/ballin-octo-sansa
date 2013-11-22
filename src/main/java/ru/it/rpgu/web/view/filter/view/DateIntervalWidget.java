/**
 * 
 */
package ru.it.rpgu.web.view.filter.view;

import java.util.Date;

import com.vaadin.ui.Component;

/**
 * @author Sergey Popov (sergey_popov@relex.ru)
 *
 */
public class DateIntervalWidget {
	final DateIntervalView view;

	public DateIntervalWidget() {
		view = new DateIntervalView();
		setHandlers();
	}
	
	private void setHandlers() {
//		ValueChangeListener dateChangeListener = new ValueChangeListener() {
//			
//			@Override
//			public void valueChange(ValueChangeEvent event) {
//				checkDatesInterval();
//			}
//		};
//		view.addFromDateChangeListener(dateChangeListener);
//		view.addToDateChangeListener(dateChangeListener);
	}

	
	private void checkDatesInterval() {
		Date fromDate = view.getFromDate();
		Date toDate = view.getToDate();
		
		if (fromDate != null && toDate != null) {
			
		}
	}
	
	public Component getView() {
		return view;
	}
}