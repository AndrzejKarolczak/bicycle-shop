package com.example.bicycleshop.backend.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = BillingAddress.DISCRIMINATOR_VALUE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class BillingAddress extends Address {
	public static final String DISCRIMINATOR_VALUE = "BILLING_ADDRESS";
	
	public BillingAddress(String streetName, String buildingNumber, String suiteNumber, String postalCode, City city) {
		super(streetName, buildingNumber, suiteNumber, postalCode, city);
	}
	
	public BillingAddress(String streetName, String buildingNumber, String postalCode, City city) {
		super(streetName, buildingNumber, postalCode, city);
	}
}
