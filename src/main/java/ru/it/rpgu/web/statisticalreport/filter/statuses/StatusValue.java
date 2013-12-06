package ru.it.rpgu.web.statisticalreport.filter.statuses;

/**
 * @author Sergey Popov (sergey_popov@relex.ru)
 *
 */
public enum StatusValue {
	ALL("Все", null),
	SEND_TO_OFFICE("Отправлено в ведомство", new SendToOfficeStrategy()),
	ERROR_SENDING_TO_OFFICE("Ошибка отправки в ведомство", new ErrorSendingtoOfficeStrategy()),
	IN_REVIEWING("В процессе рассмотрения", new InReviewingStrategy()),
	REQUIRED_ADDITIONAL_INFO("Требуются дополнительные сведения", new RequiredAdditionalInfoStrategy()),
	EXECUTED("Исполнено", new ExecutedStrategy()),
	DENIED("Отказано", new DeniedStrategy()),
	CANCELLED("Отменено", new CancelledStrategy()),
	OTHER("Прочие статусы", new OtherStrategy());
	
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
