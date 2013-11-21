package ru.it.rpgu.web.view;

import java.util.Arrays;
import java.util.List;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Sergey Popov (sergey_popov@relex.ru)
 * 
 */
class ReportForm extends CustomComponent {

	private static final String ОТЧЕТ_ПО_ЗАЯВКАМ_В_РАЗРЕЗЕ_УСЛУГ = "Отчет по заявкам в разрезе услуг";
	private static final String ОТЧЕТ_ПО_ЗАЯВКАМ_В_РАЗРЕЗЕ_ВЕДОМСТВ_С_ДЕТАЛИЗАЦИЕЙ_ПО_СТАТУСАМ = "Отчет по заявкам в разрезе ведомств с детализацией по статусам";
	private static final String ОТЧЕТ_ПО_ЗАЯВКАМ_В_РАЗРЕЗЕ_ВЕДОМСТВ = "Отчет по заявкам в разрезе ведомств";
	private static final String ОТЧЕТ_ПО_ЗАЯВКАМ_В_РАЗРЕЗЕ_УСЛУГ_С_ДЕТАЛИЗАЦИЕЙ_ПО_СТАТУСАМ = "Отчет по заявкам в разрезе услуг с детализацией по статусам";
	List<KeyValue> options = Arrays
			.asList(new KeyValue(ОТЧЕТ_ПО_ЗАЯВКАМ_В_РАЗРЕЗЕ_УСЛУГ,
					ОТЧЕТ_ПО_ЗАЯВКАМ_В_РАЗРЕЗЕ_ВЕДОМСТВ),
					new KeyValue(
							ОТЧЕТ_ПО_ЗАЯВКАМ_В_РАЗРЕЗЕ_ВЕДОМСТВ_С_ДЕТАЛИЗАЦИЕЙ_ПО_СТАТУСАМ,
							ОТЧЕТ_ПО_ЗАЯВКАМ_В_РАЗРЕЗЕ_ВЕДОМСТВ_С_ДЕТАЛИЗАЦИЕЙ_ПО_СТАТУСАМ),
					new KeyValue(ОТЧЕТ_ПО_ЗАЯВКАМ_В_РАЗРЕЗЕ_УСЛУГ,
							ОТЧЕТ_ПО_ЗАЯВКАМ_В_РАЗРЕЗЕ_УСЛУГ),
					new KeyValue(
							ОТЧЕТ_ПО_ЗАЯВКАМ_В_РАЗРЕЗЕ_УСЛУГ_С_ДЕТАЛИЗАЦИЕЙ_ПО_СТАТУСАМ,
							ОТЧЕТ_ПО_ЗАЯВКАМ_В_РАЗРЕЗЕ_УСЛУГ_С_ДЕТАЛИЗАЦИЕЙ_ПО_СТАТУСАМ));

	public ReportForm() {
		VerticalLayout mainLayout = buildMainLayout();
		setCompositionRoot(mainLayout);
	}

	private VerticalLayout buildMainLayout() {
		VerticalLayout mainLayout = new VerticalLayout();

		Label header = new Label("Отчеты");
		mainLayout.addComponent(header);

		NativeSelect reportTypeSelect = buildReportTypeSelect();
		mainLayout.addComponent(reportTypeSelect);

		DateIntervalWidget dateInterval = new DateIntervalWidget();
		mainLayout.addComponent(dateInterval.getView());

		return mainLayout;
	}

	private NativeSelect buildReportTypeSelect() {
		NativeSelect select = new NativeSelect("Выберите отчет", options);
		select.setNullSelectionAllowed(false);
		select.setValue(options.get(0));
		
		select.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
		return select;
	}

	private class KeyValue {
		private Object key;
		private Object value;

		public KeyValue(Object key, Object value) {
			this.key = key;
			this.value = value;
		}

		/**
		 * @return the key
		 */
		public Object getKey() {
			return key;
		}

		/**
		 * @param key
		 *            the key to set
		 */
		public void setKey(Object key) {
			this.key = key;
		}

		/**
		 * @return the value
		 */
		public Object getValue() {
			return value;
		}

		/**
		 * @param value
		 *            the value to set
		 */
		public void setValue(Object value) {
			this.value = value;
		}
		
		@Override
		public String toString() {
			return value.toString();
		}
	}
}
