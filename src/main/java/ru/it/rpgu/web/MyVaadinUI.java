package ru.it.rpgu.web;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("rpgu-static-report-theme")
@SuppressWarnings("serial")
public class MyVaadinUI extends UI
{

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = MyVaadinUI.class, widgetset = "ru.it.rpgu.web.AppWidgetSet")
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        setContent(layout);
        
        Table table = new Table();
        table.addContainerProperty("Наименование ведомства", String.class, null);
        table.addContainerProperty("Всего заявок", Integer.class, null);
        
        Object departments[][] = {{"Department 1", 12},
        		{"Department 2", 100},
        		{"Department 3", 15}
        };

        int total = 0;
        for (int i = 0; i < departments.length; i++) {
        	table.addItem(departments[i], i);
        	total += (Integer) departments[i][1];
        }
        
        table.setFooterVisible(true);
        table.setColumnFooter("Наименование ведомства", "Итого");
        table.setColumnFooter("Всего заявок", String.valueOf(total));
        
        layout.addComponent(table);
        
        Button button = new Button("Click Me");
        button.addClickListener(new Button.ClickListener() {
            @Override
			public void buttonClick(ClickEvent event) {
                layout.addComponent(new Label("Thank you for clicking"));
            }
        });
        layout.addComponent(button);
        
        ReportForm reportForm = new ReportForm();
        layout.addComponent(reportForm);
    }

}
