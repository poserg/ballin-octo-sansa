package ru.it.rpgu.web.statisticalreport.table;

import java.util.List;
import java.util.Set;

import ru.it.rpgu.core.model.statisticalreport.Report;
import ru.it.rpgu.web.statisticalreport.table.strategies.StatusValue;

public interface ITableController {

	/**
	 * Создание столбцов.
	 * @param checkedStatuses - выбранные статусы
	 * @param serviceCategory - нужен столбец Категория услуг
	 * @param lifeSituation - нужен столбец Жизненная ситуация
	 * @param dataList 
	 * @param isMunicipal 
	 * @param isRegional
	 */
	public abstract void setData(Set<StatusValue> checkedStatuses,
			Boolean serviceCategory, Boolean lifeSituation,
			List<Report> dataList, Boolean isMunicipal, Boolean isRegional);

}