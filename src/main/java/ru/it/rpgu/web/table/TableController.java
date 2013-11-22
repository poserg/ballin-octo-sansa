package ru.it.rpgu.web.table;


import com.vaadin.ui.Component;


/**
 * @author Sergey Popov
 *
 */
public class TableController {
	
	private final TableView tableView;
	
	public TableController() {
		tableView = new TableView();
	}

	public Component getView() {
		return tableView.getView();
	}
}
