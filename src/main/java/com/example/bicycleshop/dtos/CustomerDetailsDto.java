package com.example.bicycleshop.dtos;

import com.example.bicycleshop.backend.entities.BusinessEntity;
import com.example.bicycleshop.backend.entities.Individual;
import com.example.bicycleshop.backend.entities.Organization;
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
	
	public CustomerDetailsDto(BusinessEntity client){
		this.isIndividual = String.valueOf(client.isIndividual());
		if(client.isIndividual()){
			Individual individual = (Individual)client;
			this.billingFirstName = individual.getFirstName() ;
			this.billingLastName = individual.getLastName();
			this.shippingFirstName = individual.getFirstName(); //TODO
			this.shippingLastName = individual.getLastName();
		} else {
			Organization organization = (Organization) client;
			this.billingCompanyName = organization.getName();
			this.taxIdNumber = organization.getTaxIdNumber();
		}
		
		this.email = client.getEmail();
		this.phone = client.getPhone();
		this.billingStreet = client.getBillingAddress().getStreetName();
		this.billingBuildingNumber = client.getBillingAddress().getBuildingNumber();
		this.billingSuiteNumber = client.getBillingAddress().getSuiteNumber();
		this.billingCity = client.getBillingAddress().getCity().getName();
		this.billingPostalCode = client.getBillingAddress().getPostalCode();
		this.billingCountry = client.getBillingAddress().getCity().getCountry().getCountryId();
		this.shippingCompanyName = ""; //TODO
		this.shippingStreet =client.getShippingAddress().getStreetName();
		this.shippingBuildingNumber = client.getShippingAddress().getBuildingNumber();
		this.shippingSuiteNumber = client.getShippingAddress().getSuiteNumber();
		this.shippingCity = client.getShippingAddress().getCity().getName();
		this.shippingPostalCode = client.getShippingAddress().getPostalCode();
		this.shippingCountry = client.getShippingAddress().getCity().getCountry().getCountryId();
	}
	
	public boolean hasShippingAddress() {
		return !getShippingStreet().isEmpty() & !getShippingBuildingNumber().isEmpty() &
			!getShippingPostalCode().isEmpty() & !getShippingCity().isEmpty();
	}
}
