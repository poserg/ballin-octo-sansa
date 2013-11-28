package ru.it.rpgu.web.statisticalreport.filter.view;

import ru.it.rpgu.web.statisticalreport.filter.ServiceType;

import com.vaadin.ui.Component;
import com.vaadin.ui.OptionGroup;

/**
 * @author Sergey Popov
 *
 */
public class ServiceTypeWidget {

	private OptionGroup view;
	
	public ServiceTypeWidget() {
		buildView();
	}
	
	private void buildView() {
		view = new OptionGroup();
		view.addItem(ServiceType.ALL);
		view.addItem(ServiceType.MUNICIPAL);
		view.addItem(ServiceType.REGIONAL);
		
		view.select(ServiceType.ALL);
	}
	
	public Component getView() {
		return view;
	}
	
	public ServiceType getValue() {
		return (ServiceType) view.getValue();
	}
}
