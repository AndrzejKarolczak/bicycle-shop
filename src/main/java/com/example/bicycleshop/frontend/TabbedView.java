package com.example.bicycleshop.frontend;

import com.example.bicycleshop.backend.model.ProductAndQuantity;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route("transaction")
public class TabbedView extends HorizontalLayout {
    public static final String BROWSER_TAB_TEST = "Sklep rowerowy";
    private static final String PICTURE_NOT_FOUND = "Nie znaleziono";
    private IBasketContentService basketContentService;

    @Autowired
    public TabbedView(IBasketContentService basketContentService) {
        this.basketContentService = basketContentService;
        H1 a = new H1("Czemu nic nie widać?");
        Tabs tabs = new Tabs();
        tabs.setOrientation(Tabs.Orientation.HORIZONTAL);
        Tab basket = new Tab("Twój koszyk");
        basket.setEnabled(true);
        basket.add(getGrid());


        Grid<ProductAndQuantity> g = getGrid();
        Tab address = new Tab("Dane do dostawy");
        address.setEnabled(false);
        tabs.add(basket, address);
        add(a, g);
    }

    private Grid<ProductAndQuantity> getGrid() {
        List<ProductAndQuantity> contents = basketContentService.getBasketContents();

        Grid<ProductAndQuantity> grid = new Grid<>();
        grid.addComponentColumn(t -> {
            Image i = new Image(t.getProduct().getLinkToPicture(), PICTURE_NOT_FOUND);
            i.setHeight("50px");
            return i;
        });

        grid.addColumn(r -> r.getProduct().getName()).setHeader("Nazwa");
        grid.addComponentColumn(this::getNumberField).setHeader("Ilość");
        grid.setItems(contents);
        grid.getColumns()
            .forEach(personColumn -> personColumn.setAutoWidth(true));

        return grid;
    }

    private NumberField getNumberField(ProductAndQuantity productAndQuantity) {
        NumberField quantity = new NumberField();
        quantity.setMin(0);
        quantity.setMax(100); //TODO sparametryzować
        quantity.setValue((double) productAndQuantity.getQuantity());
        quantity.setStep(1);
        quantity.setHasControls(true);
        quantity.addValueChangeListener(r -> {
                productAndQuantity.setQuantity(r.getValue().intValue());

            }
        );

        return quantity;
    }
}
