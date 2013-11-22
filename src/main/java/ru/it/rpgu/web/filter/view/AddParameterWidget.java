package ru.it.rpgu.web.filter.view;

import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

/**
 * Панель с дополнительными параметрами.
 * 
 * @author Sergey Popov
 *
 */
public class AddParameterWidget {

	private static final String SHOW_LIFE_SITUATION = "Отображать жизненную ситуацию";

	private static final String SHOW_SERVICE_CATEGORY = "Отображать категорию услуг";

	final Component view;
	
	CheckBox serviceCategoryCheckBox, lifeSituationCheckBox;
	
	public AddParameterWidget() {
		view = buildView();
	}

	private Component buildView() {
		VerticalLayout layout = new VerticalLayout();
		serviceCategoryCheckBox = new CheckBox(SHOW_SERVICE_CATEGORY);
		layout.addComponent(serviceCategoryCheckBox);
		
		lifeSituationCheckBox = new CheckBox(SHOW_LIFE_SITUATION);
		layout.addComponent(lifeSituationCheckBox);
		
		return layout;
	}
	
	public Component getView() {
		return view;
	}
	
	public boolean getServiceCategoryValue() {
		return serviceCategoryCheckBox.getValue();
	}
	
	public boolean getLifeSituationValue() {
		return lifeSituationCheckBox.getValue();
	}
}
