/*package com.example.bicycleshop.backend.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = ShippingAddress.DISCRIMINATOR_VALUE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class ShippingAddress extends Address {
	public static final String DISCRIMINATOR_VALUE = "SHIPPING_ADDRESS";
	
	public ShippingAddress(String streetName, String buildingNumber, String suiteNumber, String postalCode, City city) {
		super(streetName, buildingNumber, suiteNumber, postalCode, city);
	}
	
	public ShippingAddress(String streetName, String buildingNumber, String postalCode, City city) {
		super(streetName, buildingNumber, postalCode, city);
	}
}*/
