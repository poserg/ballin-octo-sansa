package ru.it.rpgu.web.filter.view;

/**
 * @author Sergey Popov (sergey_popov@relex.ru)
 *
 */
enum ServiceType {
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
