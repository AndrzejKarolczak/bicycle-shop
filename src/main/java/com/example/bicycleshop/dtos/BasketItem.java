package com.example.bicycleshop.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasketItem{
	private long id;
	private int quantity;
	private double price;
	
	public BasketItem() {
	}
	
	public BasketItem(int id, int quantity, double price) {
		this.id = id;
		this.quantity = quantity;
		this.price = price;
	}
	
//	public long getId() {
//		return id;
//	}
//
//	public void setId(long id) {
//		this.id = id;
//	}
//
//	public int getQuantity() {
//		return quantity;
//	}
//
//	public void setQuantity(int quantity) {
//		this.quantity = quantity;
//	}
//
//	public double getPrice() {
//		return price;
//	}
//
//	public void setPrice(double price) {
//		this.price = price;
//	}
}