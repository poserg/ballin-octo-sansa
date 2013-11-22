package ru.it.rpgu.web.filter;

import java.util.Date;
import java.util.List;

/**
 * @author Sergey Popov
 *
 */
public class FilterState {

	/**
	 * Дата начала.
	 */
	Date fromDate;

	/**
	 * Дата завершения.
	 */
	Date toDate;

	/**
	 * Список выбранных статусов.
	 */
	List<StatusValue> checkedStatuses;

	/**
	 * Вид услуг.
	 */
	ServiceType serviceType;

	/**
	 * Необходимы категории услуг.
	 */
	Boolean serviceCategory;

	/**
	 * Необходимы жизненные ситуации.
	 */
	Boolean lifeSituation;
	
	/**
	 * @return the fromDate
	 */
	public Date getFromDate() {
		return fromDate;
	}

	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * @return the toDate
	 */
	public Date getToDate() {
		return toDate;
	}

	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	/**
	 * @return the checkedStatuses
	 */
	public List<StatusValue> getCheckedStatuses() {
		return checkedStatuses;
	}

	/**
	 * @param checkedStatuses the checkedStatuses to set
	 */
	public void setCheckedStatuses(List<StatusValue> checkedStatuses) {
		this.checkedStatuses = checkedStatuses;
	}

	/**
	 * @return the serviceType
	 */
	public ServiceType getServiceType() {
		return serviceType;
	}

	/**
	 * @param serviceType the serviceType to set
	 */
	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}

	/**
	 * @return the serviceCategory
	 */
	public Boolean getServiceCategory() {
		return serviceCategory;
	}

	/**
	 * @param serviceCategory the serviceCategory to set
	 */
	public void setServiceCategory(Boolean serviceCategory) {
		this.serviceCategory = serviceCategory;
	}

	/**
	 * @return the lifeSituation
	 */
	public Boolean getLifeSituation() {
		return lifeSituation;
	}

	/**
	 * @param lifeSituation the lifeSituation to set
	 */
	public void setLifeSituation(Boolean lifeSituation) {
		this.lifeSituation = lifeSituation;
	}
}
