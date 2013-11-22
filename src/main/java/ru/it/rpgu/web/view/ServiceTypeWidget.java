package ru.it.rpgu.web.view;

import com.vaadin.data.Container;
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
	}
	
	public Container getView() {
		return view;
	}
	
	public ServiceType getValue() {
		return (ServiceType) view.getValue();
	}
}
