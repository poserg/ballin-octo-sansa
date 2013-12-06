package ru.it.rpgu.web.statisticalreport.filter;

import java.util.Date;
import java.util.Set;

import ru.it.rpgu.web.statisticalreport.filter.statuses.StatusValue;
import ru.it.rpgu.web.statisticalreport.filter.strategies.IFilterStrategy;
import ru.it.rpgu.web.statisticalreport.filter.strategies.ReportTypeEnum;

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
		
		/**
		 * Получить виджет с дополнительными параметрами.
		 * @return
		 */
		Component getAddParameterComponent();
		
		Component getMainLayout();
		
		/**
		 * Установить тип отчёта.
		 * @param reportType
		 */
		void setReportType(ReportTypeEnum reportType);
		
		/**
		 * Добавление виджета в нижнюю панель без удаления существующих.
		 * @param component
		 */
		void addComponentToBottomLayout(Component component);
		
		/**
		 * Получить дату начала.
		 * @return
		 */
		Date getFromDate();
		
		/**
		 * Получить дату завершения.
		 * @return
		 */
		Date getToDate();
		
		/**
		 * Получить список выбранных статусов.
		 * @return
		 */
		Set<StatusValue> getCheckedStatuses();
		
		/**
		 * Получить тип услуг.
		 * @return
		 */
		ServiceType getServiceType();
		
		/**
		 * Следует ли отображать категории услуг.
		 * @return
		 */
		boolean getServiceCategory();
		
		/**
		 * Следует ли отображать жизненные ситуации.
		 * @return
		 */
		boolean getLifeSituation();
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
			
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				IFilterStrategy strategy = getCurrentFilterStrategy();
				strategy.buildFilterLayout(view);
			}

		});
	}
	
	public Component getView() {
		return view.getMainLayout();
	}

	public IFilterStrategy getCurrentFilterStrategy() {
		IFilterStrategy strategy = view.getCurrentReportType().getStrategy();
		return strategy;
	}
	
	public FilterState getCurrentFilterState() {
		IFilterStrategy currentFilterStrategy = getCurrentFilterStrategy();
		return currentFilterStrategy.getCurrentFilterState(view);
	}
}
