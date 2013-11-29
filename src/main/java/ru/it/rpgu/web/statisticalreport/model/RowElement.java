package ru.it.rpgu.web.statisticalreport.model;

/**
 * @author Sergey Popov
 *
 */
public class RowElement {

	/**
	 * Идентификатор ведомства
	 */
	private Long deparmentId;
	
	/**
	 * Название ведомства
	 */
	private String departmentName;
	
	/**
	 * Идентификатор статуса
	 */
	private Long stateId;
	
	/**
	 * Название статуса
	 */
	private String stateName;
	
	/**
	 * Идентификатор услуги
	 */
	private Long serviceId;
	
	/**
	 * Название услуги
	 */
	private String serviceName;
	
	/**
	 * Категория услуг
	 */
	private String category;
	
	/**
	 * Жизненная ситуация
	 */
	private String lifeSituation;
	
	/**
	 * Количество заявлений
	 */
	private Long applicationCount;

	/**
	 * @return the deparmentId
	 */
	public Long getDeparmentId() {
		return deparmentId;
	}

	/**
	 * @param deparmentId the deparmentId to set
	 */
	public void setDeparmentId(Long deparmentId) {
		this.deparmentId = deparmentId;
	}

	/**
	 * @return the departmentName
	 */
	public String getDepartmentName() {
		return departmentName;
	}

	/**
	 * @param departmentName the departmentName to set
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	/**
	 * @return the stateId
	 */
	public Long getStateId() {
		return stateId;
	}

	/**
	 * @param stateId the stateId to set
	 */
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	/**
	 * @return the stateName
	 */
	public String getStateName() {
		return stateName;
	}

	/**
	 * @param stateName the stateName to set
	 */
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	/**
	 * @return the serviceId
	 */
	public Long getServiceId() {
		return serviceId;
	}

	/**
	 * @param serviceId the serviceId to set
	 */
	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	/**
	 * @return the serviceName
	 */
	public String getServiceName() {
		return serviceName;
	}

	/**
	 * @param serviceName the serviceName to set
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the lifeSituation
	 */
	public String getLifeSituation() {
		return lifeSituation;
	}

	/**
	 * @param lifeSituation the lifeSituation to set
	 */
	public void setLifeSituation(String lifeSituation) {
		this.lifeSituation = lifeSituation;
	}

	/**
	 * @return the applicationCount
	 */
	public Long getApplicationCount() {
		return applicationCount;
	}

	/**
	 * @param applicationCount the applicationCount to set
	 */
	public void setApplicationCount(Long applicationCount) {
		this.applicationCount = applicationCount;
	}
}
