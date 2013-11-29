package ru.it.rpgu.web.statisticalreport.table.strategies;

/**
 * @author Sergey Popov (sergey_popov@relex.ru)
 *
 */
public enum StatusValue {
	ALL("Все", null),
	SEND_TO_OFFICE("Отправлено в ведомство", new SendToOfficeStrategy()),
	ERROR_SENDING_TO_OFFICE("Ошибка отправки в ведомство", null),
	IN_REVIEWING("В процессе рассмотрения", null),
	REQUIRED_ADDITIONAL_INFO("Требуются дополнительные сведения", null),
	EXECUTED("Исполнено", null),
	DENIED("Отказано", null),
	CANCELLED("Отменено", null),
	OTHER("Прочие статусы", null);
	
	private final String value;
	private final ITableColStrategy strategy;
	
	StatusValue(String value, ITableColStrategy strategy) {
		this.value = value;
		this.strategy = strategy;
	}
	
	public String getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return getValue();
	}

	/**
	 * @return the strategy
	 */
	public ITableColStrategy getStrategy() {
		return strategy;
	}
}
