package com.example.bicycleshop;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDetailsForm {
	private String sessionId;
	private String basketContents;
	private String billingFirstName;
	private String billingLastName;
	private String billingCompanyName;
	private String email;
	private String phone;
	private String billingStreet;
	private String billingBuildingNumber;
	private String billingSuiteNumber;
	private String billingCity;
	private String billingPostalCode;
	private Integer billingCountry;
	private String shippingFirstName;
	private String shippingLastName;
	private String shippingCompanyName;
	private String shippingStreet;
	private String shippingBuildingNumber;
	private String shippingSuiteNumber;
	private String shippingCity;
	private String shippingPostalCode;
	private Integer shippingCountry;
	private String passwordFirst;
}
