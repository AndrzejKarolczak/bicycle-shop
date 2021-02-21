package com.example.bicycleshop.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerDetailsDto {
	private String sessionId;
	private String basketContents;
	private String isIndividual;
	private String billingFirstName;
	private String billingLastName;
	private String billingCompanyName;
	private String taxIdNumber;
	private String email;
	private String phone;
	private String billingStreet;
	private String billingBuildingNumber;
	private String billingSuiteNumber;
	private String billingCity;
	private String billingPostalCode;
	private Long billingCountry;
	private String shippingFirstName;
	private String shippingLastName;
	private String shippingCompanyName;
	private String shippingStreet;
	private String shippingBuildingNumber;
	private String shippingSuiteNumber;
	private String shippingCity;
	private String shippingPostalCode;
	private Long shippingCountry;
	private String passwordFirst;
	
	public CustomerDetailsDto(String sessionId) {
		this.sessionId = sessionId;
	}
	
	public boolean hasShippingAddress() {
		return !getShippingStreet().isEmpty() & !getShippingBuildingNumber().isEmpty() &
			!getShippingPostalCode().isEmpty() & !getShippingCity().isEmpty();
	}
}
