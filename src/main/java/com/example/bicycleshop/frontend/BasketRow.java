package com.example.bicycleshop.frontend;

import com.example.bicycleshop.backend.model.Product;
import com.example.bicycleshop.backend.model.ProductAndQuantity;
import com.example.bicycleshop.backend.model.ProductInOrder;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;

public class BasketRow extends HorizontalLayout {

    private ProductAndQuantity productAndQuantity;

    public BasketRow(ProductAndQuantity productAndQuantity) {
        this.productAndQuantity = productAndQuantity;

        addClassName("basket-row");

        Image image = getImage();
        Label label = getLabel();

        NumberField quantity = getNumberField();

        add(image, label, quantity);
        //setAlignItems(Alignment.START);
    }

    private Label getLabel() {
        Label label = new Label(productAndQuantity.getProduct().getName());
        return  label;
    }

    private Image getImage(){
        Image image = new Image(productAndQuantity.getProduct().getLinkToPicture(), "");

        return image;
    }

    private NumberField getNumberField() {
        NumberField quantity = new NumberField("Ilość");
        quantity.setMin(0);
        quantity.setMax(100); //TODO sparametryzować
        quantity.setValue((double)productAndQuantity.getQuantity() );
        quantity.setStep(1);
        quantity.setHasControls(true);
        return quantity;
    }

}
