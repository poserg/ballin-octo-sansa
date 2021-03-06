package ru.it.rpgu.web.statisticalreport.filter.strategies;


/**
 * @author Sergey Popov (sergey_popov@relex.ru)
 *
 */
public enum ReportTypeEnum {
	SERVICE("Отчет по заявкам в разрезе услуг", new ServiceFilterStrategy()),
	SERVICE_AND_STATUSES("Отчет по заявкам в разрезе услуг с детализацией по статусам", new ServiceAndStatusesFilterStrategy()),
	OFFICE("Отчет по заявкам в разрезе ведомств", new OfficeFilterStrategy()),
	OFFICE_AND_STATUSES("Отчет по заявкам в разрезе ведомств с детализацией по статусам", new OfficeAndStatusesFilterStrategy());
	
	private final String value;
	private final IFilterStrategy strategy;

	private ReportTypeEnum(String value, IFilterStrategy strategy) {
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
