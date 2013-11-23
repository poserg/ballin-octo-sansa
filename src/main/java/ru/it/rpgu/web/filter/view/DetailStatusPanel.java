package ru.it.rpgu.web.filter.view;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ru.it.rpgu.web.filter.StatusValue;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.VerticalLayout;

public class DetailStatusPanel extends VerticalLayout {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8069212395641221251L;
	private static final String DETAIL_BY_STATUS = "Детализация по статусам:";
	
	private static final Set<StatusValue> statusValues = new HashSet<StatusValue>() {
		private static final long serialVersionUID = 1L;

		{
			this.add(StatusValue.SEND_TO_OFFICE);
			this.add(StatusValue.ERROR_SENDING_TO_OFFICE);
			this.add(StatusValue.IN_REVIEWING);
			this.add(StatusValue.REQUIRED_ADDITIONAL_INFO);
			this.add(StatusValue.EXECUTED);
			this.add(StatusValue.DENIED);
			this.add(StatusValue.CANCELLED);
		}
	};

	final List<CheckBox> checkList;
	final CheckBox mainCheckBox;
	final OptionGroup checkBoxGroup;

	public DetailStatusPanel() {
		super();

		addComponent(new Label(DETAIL_BY_STATUS));
		
		mainCheckBox = new CheckBox(StatusValue.ALL.getValue());
		mainCheckBox.setValue(true);
		addComponent(mainCheckBox);
		checkBoxGroup = new OptionGroup();
		checkBoxGroup.setMultiSelect(true);
		checkBoxGroup.setEnabled(false);
		addComponent(checkBoxGroup);

		mainCheckBox.addValueChangeListener(new ValueChangeListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 6895499615951782277L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				checkBoxGroup.setEnabled(!mainCheckBox.getValue());
			}
		});

		checkList = new ArrayList<CheckBox>();
		buildMainLayout(checkBoxGroup);
	}

	private void buildMainLayout(OptionGroup checkBoxGroup) {
		for (StatusValue statusValue : statusValues) {
			checkBoxGroup.addItem(statusValue);
		}
	}

	private boolean isAllCheck() {
		return mainCheckBox.getValue();
	}

	public Set<StatusValue> getStatuses() {
		if (isAllCheck()) {
			return statusValues;
		} else {
			return (Set<StatusValue>) checkBoxGroup.getValue();
		}
	}
}
