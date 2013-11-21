package ru.it.rpgu.web.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.VerticalLayout;

public class DetailStatusPanel extends VerticalLayout {
	private static final String DETAIL_BY_STATUS = "Детализация по статусам:";
	final List<StatusCheckBox> checkList;
	final CheckBox mainCheckBox;
	final OptionGroup checkBoxGroup;

	public DetailStatusPanel() {
		super();

		addComponent(new Label(DETAIL_BY_STATUS));
		
		mainCheckBox = new CheckBox(StatusValue.ALL.getValue());
		addComponent(mainCheckBox);
		checkBoxGroup = new OptionGroup();
		checkBoxGroup.setMultiSelect(true);
		addComponent(checkBoxGroup);

		mainCheckBox.addValueChangeListener(new ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				checkBoxGroup.setEnabled(!mainCheckBox.getValue());
			}
		});

		checkList = new ArrayList<StatusCheckBox>();
		buildMainLayout(checkBoxGroup);
	}

	private void buildMainLayout(OptionGroup checkBoxGroup) {
		checkBoxGroup.addItem(StatusValue.SEND_TO_OFFICE);
		checkBoxGroup.addItem(StatusValue.ERROR_SENDING_TO_OFFICE);
		checkBoxGroup.addItem(StatusValue.IN_REVIEWING);
		checkBoxGroup.addItem(StatusValue.REQUIRED_ADDITIONAL_INFO);
		checkBoxGroup.addItem(StatusValue.EXECUTED);
		checkBoxGroup.addItem(StatusValue.DENIED);
		checkBoxGroup.addItem(StatusValue.CANCELLED);
	}

	private CheckBox addCheckBox(StatusValue status) {
		StatusCheckBox checkBox = new StatusCheckBox(status);
		checkList.add(checkBox);

		return checkBox;
	}

	private boolean isAllCheck() {
		return mainCheckBox.getValue();
	}

	public List<StatusValue> getStatuses() {
		if (isAllCheck()) {
			return Arrays.asList(StatusValue.ALL);
		} else {
			return (List<StatusValue>) checkBoxGroup.getValue();
		}
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
