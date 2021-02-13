package com.example.bicycleshop;

public class BasketItem{
	public int id;
	public int quantity;
	public double price;
	
	public BasketItem(int id, int quantity, double price) {
		this.id = id;
		this.quantity = quantity;
		this.price = price;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
}