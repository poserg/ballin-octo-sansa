package ru.it.rpgu.web.filter;

/**
 * @author Sergey Popov (sergey_popov@relex.ru)
 *
 */
public enum ServiceType {
	ALL("Все услуги"),
	MUNICIPAL("Муниципальные услуги"),
	REGIONAL("Региональные услуги");
	
	private final String value;

	private ServiceType(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;
	}
}
