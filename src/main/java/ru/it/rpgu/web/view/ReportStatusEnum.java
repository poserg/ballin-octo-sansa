package ru.it.rpgu.web.view;

/**
 * @author Sergey Popov (sergey_popov@relex.ru)
 *
 */
enum ReportStatusEnum {
	SERVICE("Отчет по заявкам в разрезе услуг", null),
	SERVICE_AND_STATUSES("Отчет по заявкам в разрезе услуг с детализацией по статусам", null),
	OFFICE("Отчет по заявкам в разрезе ведомств", null),
	OFFICE_AND_STATUSES("Отчет по заявкам в разрезе ведомств с детализацией по статусам", null);
	
	private final String value;
	private final IFilterStrategy strategy;

	private ReportStatusEnum(String value, IFilterStrategy strategy) {
		this.value = value;
		this.strategy = strategy;
	}
	
	@Override
	public String toString() {
		return value;
	}

	/**
	 * @return the strategy
	 */
	public IFilterStrategy getStrategy() {
		return strategy;
	}
}
