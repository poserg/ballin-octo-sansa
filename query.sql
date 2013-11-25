select count(app_hist_id), state_name, agency_office_name from (select application_id as app_id, max(date_inserted), max(application_state_history_id) as app_hist_id
    	    from public.po_application_state_history
		where date_inserted >= '2013-11-01'
		and date_inserted <= '2013-12-31 23:59:59'
        	group by application_id) sub_pash, po_application_state_history pash,
        	po_application pa where
        	sub_pash.app_hist_id = pash.application_state_history_id 
        	and pa.application_id = sub_pash.app_id
        	group by agency_office_name, state_name

select short_name, state_name, count(app_hist_id) from (select application_id as app_id, max(date_inserted), max(application_state_history_id) as app_hist_id
    	    from public.po_application_state_history
		where date_inserted >= '2013-11-01'
		and date_inserted <= '2013-12-31 23:59:59'
        	group by application_id) sub_pash, po_application_state_history pash,
        	po_application pa, cat_service ca where
        	sub_pash.app_hist_id = pash.application_state_history_id 
        	and pa.application_id = sub_pash.app_id
        	and pa.service_id = ca.service_id
        	group by short_name, state_name
