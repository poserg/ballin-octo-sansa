INSERT INTO public.layout
select '45e016c0-5690-11e3-949a-0800200c9a66', max(plid)+1, 10180, 10154, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, false, max(layoutid)+1, 8, '<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="ru_RU" default-locale="ru_RU"><Name language-id="ru_RU">־עקועû</Name></root>', '', '', '', '', 'portlet', 'lfr-theme:regular:portlet-setup-show-borders-default=false
sitemap-changefreq=daily
layout-template-id=1_column
show-alternate-links=true
sitemap-include=1
layoutUpdateable=true
column-2-customizable=false
column-1-customizable=false
modifiedDate=20130723164046
column-1=staticreportportlet_WAR_rpgustaticreportportlet_INSTANCE_gCz3f4iqrgSR
', false, '/static_report', false, 0, '', '', 'classic', '', '', 12, '', false, ''
from public.layout;
 

INSERT INTO public.resourcepermission
select max(r.resourcepermissionid)+1, 10154, 'com.liferay.portal.model.Layout', 4, max (l.plid), 10163, 0, 1023
from public.resourcepermission r, public.layout l
where l.uuid_ = '45e016c0-5690-11e3-949a-0800200c9a66';

INSERT INTO public.resourcepermission
select max(r.resourcepermissionid)+1, 10154, 'com.liferay.portal.model.Layout', 4, max (l.plid), 10162, 0, 0
from public.resourcepermission r, public.layout l
where l.uuid_ = '45e016c0-5690-11e3-949a-0800200c9a66';

