/**
 * 
 */
package ru.it.rpgu.web.filter;

/**
 * @author Sergey Popov (sergey_popov@relex.ru)
 *
 */
public enum StatusValue {
	ALL("Все"),
	SEND_TO_OFFICE("Отправлено в ведомство"),
	ERROR_SENDING_TO_OFFICE("Ошибка отправки в ведомство"),
	IN_REVIEWING("В процессе рассмотрения"),
	REQUIRED_ADDITIONAL_INFO("Требуются дополнительные сведения"),
	EXECUTED("Исполнено"),
	DENIED("Отказано"),
	CANCELLED("Отменено");
	
	private String value;
	
	StatusValue(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return value;
	}
}
