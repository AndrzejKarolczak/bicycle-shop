package com.example.bicycleshop.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class BasketItem{
	private long id;
	private int quantity;
	private BigDecimal price;
	
	public BasketItem() {
	}
	
	public BasketItem(int id, int quantity, double price) {
		this.id = id;
		this.quantity = quantity;
		this.price = BigDecimal.valueOf(price) ;
	}
}