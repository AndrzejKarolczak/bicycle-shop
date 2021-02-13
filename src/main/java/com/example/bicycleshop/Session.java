package com.example.bicycleshop;

import java.util.List;

public class Session {
	private String sessionId;
	private List<BasketItem> basketContents;
	
	public Session(String sessionId, List<BasketItem> basketContents) {
		this.sessionId = sessionId;
		this.basketContents = basketContents;
	}
}
