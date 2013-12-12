package ru.it.rpgu.web.statisticalreport.filter.strategies;

import java.util.Date;
import java.util.Set;

import ru.it.rpgu.web.statisticalreport.filter.FilterState;
import ru.it.rpgu.web.statisticalreport.filter.statuses.StatusValue;

import com.vaadin.ui.Notification;

/**
 * @author Sergey Popov
 *
 */
class FilterStateValidator {
	
	private static boolean validateDates(StringBuilder message, Date fromDate, Date toDate) {
		boolean isError = false;
		Date today = new Date();

		if (fromDate != null && fromDate.after(today)) {
			message.append("\nДата начала ещё не наступила.");
			isError = true;
		}

		if (toDate != null && toDate.after(today)) {
			message.append("\nДата завершения ещё не наступила.");
			isError = true;
		}

		if (fromDate != null && toDate != null && fromDate.after(toDate)) {
			message.append("\nДата начала позже даты завершения.");
			isError = true;
		}

		return isError;
	}
	
	private static boolean validateStatuses(StringBuilder message, Set<StatusValue> statusesSet) {
		boolean isError = false;
		
		if (statusesSet == null || statusesSet.size() == 0) {
			isError = true;
			message.append("\nНеобходимо выбрать один или несколько статусов.");
		}
		return isError;
	}
	
	public static boolean validateAll(FilterState filterState) {
		boolean isError = false;
		StringBuilder message = new StringBuilder();
		isError |= validateDates(message, filterState.getFromDate(), filterState.getToDate());
		isError |= validateStatuses(message, filterState.getCheckedStatuses());
		
		showMessage(isError, message);

		return !isError;
	}

	/**
	 * @param isError
	 * @param message
	 */
	private static void showMessage(boolean isError, StringBuilder message) {
		if (isError) {
			Notification.show("Ошибки в заполнении фильра:",
					message.toString(), Notification.TYPE_WARNING_MESSAGE);
		}
	}
	
	public static boolean validateDates(Date fromDate, Date toDate) {
		StringBuilder message = new StringBuilder();;
		boolean isError = validateDates(message , fromDate, toDate);
		
		showMessage(isError, message);
		
		return !isError;
	}
}
