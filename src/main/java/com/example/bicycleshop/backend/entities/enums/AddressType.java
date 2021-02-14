package com.example.bicycleshop.backend.entities.enums;

import com.example.bicycleshop.backend.entities.BillingAddress;
import com.example.bicycleshop.backend.entities.ShippingAddress;

public enum AddressType {
	BILLING(BillingAddress.DISCRIMINATOR_VALUE), SHIPPING(ShippingAddress.DISCRIMINATOR_VALUE);
	
	private final String discriminatorValue;
	
	AddressType(String discriminatorValue) {
		this.discriminatorValue = discriminatorValue;
	}
	
	public String getDiscriminatorValue() {
		return discriminatorValue;
	}
}
