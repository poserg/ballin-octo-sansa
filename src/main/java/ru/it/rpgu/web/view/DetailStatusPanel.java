package ru.it.rpgu.web.view;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class DetailStatusPanel extends VerticalLayout {
	private static final String DETAIL_BY_STATUS = "Детализация по статусам:";
	final List<StatusCheckBox> checkList;
	final CheckBox mainCheckBox;

	public DetailStatusPanel() {
		super();

		addComponent(new Label(DETAIL_BY_STATUS));
		
		mainCheckBox = new CheckBox(StatusValue.ALL.getValue());
		addComponent(mainCheckBox);
		final VerticalLayout otherStatusLayout = new VerticalLayout();
		addComponent(otherStatusLayout);

		mainCheckBox.addValueChangeListener(new ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				otherStatusLayout.setEnabled(!mainCheckBox.getValue());
			}
		});

		checkList = new ArrayList<StatusCheckBox>();
		buildMainLayout(otherStatusLayout);
	}

	private void buildMainLayout(VerticalLayout layout) {
		layout.addComponent(addCheckBox(StatusValue.SEND_TO_OFFICE));
		layout.addComponent(addCheckBox(StatusValue.ERROR_SENDING_TO_OFFICE));
		layout.addComponent(addCheckBox(StatusValue.IN_REVIEWING));
		layout.addComponent(addCheckBox(StatusValue.REQUIRED_ADDITIONAL_INFO));
		layout.addComponent(addCheckBox(StatusValue.EXECUTED));
		layout.addComponent(addCheckBox(StatusValue.DENIED));
		layout.addComponent(addCheckBox(StatusValue.CANCELLED));
	}

	private CheckBox addCheckBox(StatusValue status) {
		StatusCheckBox checkBox = new StatusCheckBox(status);
		checkList.add(checkBox);

		return checkBox;
	}

	public boolean isAllCheck() {
		return mainCheckBox.getValue();
	}

	public List<StatusValue> getStatuses() {
		List<StatusValue> result = new ArrayList<StatusValue>();
		if (!isAllCheck()) {
			for (StatusCheckBox checkBox : checkList) {
				if (checkBox.getValue()) {
					result.add(checkBox.getStatusValue());
				}
			}
		} else {
			result.add(StatusValue.ALL);
		}
		return result;
	}

	private class StatusCheckBox extends CheckBox {
		private final StatusValue statusValue;

		public StatusCheckBox(StatusValue statusValue) {
			super(statusValue.getValue());
			this.statusValue = statusValue;
		}

		/**
		 * @return the statusValue
		 */
		public StatusValue getStatusValue() {
			return statusValue;
		}
	}
}
