package ru.it.rpgu.web.view;

import com.vaadin.ui.Component;

/**
 * @author Sergey Popov (sergey_popov@relex.ru)
 *
 */
public class FilterController {
	
	public interface IFilterView {
		
		/**
		 * Установить компонент справа.
		 * @param component - null, если нужно очистить
		 */
		void setRightLayout(Component component);
		
		
	}

	final IFilterView view;
	
	public FilterController() {
		view = new FilterView();
	}
}
