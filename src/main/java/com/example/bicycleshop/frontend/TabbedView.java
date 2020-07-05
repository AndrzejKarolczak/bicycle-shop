package com.example.bicycleshop.frontend;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;

public class TabbedView extends VerticalLayout {
    public static final String BROWSER_TAB_TEST = "Sklep rowerowy";

    public TabbedView() {
        Tabs tabs = new Tabs();
        Tab basket = new Tab("Twój koszyk");
        Tab address = new Tab("Dane do dostawy");


    }
}
