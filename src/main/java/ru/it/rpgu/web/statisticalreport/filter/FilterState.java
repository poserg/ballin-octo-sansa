package ru.it.rpgu.web.statisticalreport.filter;

import java.util.Date;
import java.util.Set;

import ru.it.rpgu.core.model.statisticalreport.ReportFilterStateModel;
import ru.it.rpgu.web.statisticalreport.table.strategies.StatusValue;

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
	Set<StatusValue> checkedStatuses;

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
	public Set<StatusValue> getCheckedStatuses() {
		return checkedStatuses;
	}

	/**
	 * @param checkedStatuses the checkedStatuses to set
	 */
	public void setCheckedStatuses(Set<StatusValue> checkedStatuses) {
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
	
	public static ReportFilterStateModel toSearchParam(FilterState filterState) {
		ReportFilterStateModel searchParam = new ReportFilterStateModel();
		searchParam.setFromDate(filterState.getFromDate());
		searchParam.setToDate(filterState.getToDate());

		if (filterState.getCheckedStatuses() != null) {
			for (StatusValue statusValue : filterState.getCheckedStatuses()) {
				statusValue.getStrategy().setCheck(searchParam);
			}
		}
		
		searchParam.setServiceCategory(filterState.getServiceCategory());
		searchParam.setLifeSituation(filterState.getLifeSituation());
		
		ServiceType serviceType = filterState.getServiceType();
		if (serviceType != null) {
        		if (serviceType.equals(ServiceType.ALL)) {
	        		searchParam.setIsMunicipal(true);
	        		searchParam.setIsRegional(true);
	        	} else if (serviceType.equals(ServiceType.MUNICIPAL)) {
	        		searchParam.setIsMunicipal(true);
	        		searchParam.setIsRegional(false);
        		} else if (serviceType.equals(ServiceType.REGIONAL)) {
        			searchParam.setIsMunicipal(false);
        			searchParam.setIsRegional(true);
        		}
        	}
		
		return searchParam;
	}
}
