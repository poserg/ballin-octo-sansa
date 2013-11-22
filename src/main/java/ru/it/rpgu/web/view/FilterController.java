package ru.it.rpgu.web.view;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
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
		
		/**
		 * Установить компонент снизу
		 * @param component - null, если нужно очистить.
		 */
		void setBottomLayout(Component component);

		/**
		 * Установка слушателя для списка с типами отчётов.
		 * @param listener
		 */
		void setChangeReportTypeListener(ValueChangeListener listener);
		
		/**
		 * Получить виджет выбора статусов.
		 * @return
		 */
		Component getDetailStatusPanel();
		
		/**
		 * Получить виджет выбора типа услуги.
		 * @return
		 */
		Component getServiceTypePanel();
		
		/**
		 * Получить выбранный тип отчёта.
		 * @return
		 */
		ReportTypeEnum getCurrentReportType();
		
		Component getMainLayout();
		
		/**
		 * Установить тип отчёта.
		 * @param reportType
		 */
		void setReportType(ReportTypeEnum reportType);
	}

	final IFilterView view;
	
	public FilterController() {
		view = new FilterView();
		setHandlers();
		
		// Первоначальная расстановка элементов
		view.setReportType(ReportTypeEnum.OFFICE);
	}

	private void setHandlers() {
		view.setChangeReportTypeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				IFilterStrategy strategy = view.getCurrentReportType().getStrategy();
				strategy.buildFilterLayout(view);
			}
		});
	}
	
	public Component getView() {
		return view.getMainLayout();
	}
}
