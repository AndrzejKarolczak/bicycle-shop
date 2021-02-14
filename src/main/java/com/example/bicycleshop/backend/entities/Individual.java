package com.example.bicycleshop.backend.entities;

import com.example.bicycleshop.validation.ValidationMessages;
import com.example.bicycleshop.security.entities.Account;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "individuals")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Individual extends BusinessEntity {
    @NotBlank(message = ValidationMessages.NOT_BLANK)
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank(message = ValidationMessages.NOT_BLANK)
    @Column(name = "last_name", nullable = false)
    private String lastName;
    
    public Individual(String firstName, String lastName, Address billingAddress, Address shippingAddress,String email, String phone) {
        super(billingAddress,shippingAddress, email, phone);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Individual(String firstName, String lastName, Address billingAddress, Address shippingAddress,String email, String phone, Account account) {
        super(billingAddress, shippingAddress, email, phone, account);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Individual{" +
            "businessEntityId=" + getBusinessEntityId() +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", address=" + getBillingAddress() +
//            ", email='" + getEmail() + '\'' +
            '}';
    }
    
    @Override
    public String getName() {
        return firstName + " " + lastName;
    }
}
