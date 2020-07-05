package com.example.bicycleshop.backend.model;

import com.example.bicycleshop.helpers.ValidationMessages;
import com.example.bicycleshop.security.model.Account;
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

    @Column(name = "tax_id_number", unique = true)
    private String taxIdNumber;

    public Individual(String firstName, String lastName, Address address, String email) {
        super(address, email);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Individual(String firstName, String lastName, Address address, String email, Account account) {
        super(address, email, account);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Individual{" +
            "businessEntityId=" + getBusinessEntityId() +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", taxIdNumber='" + taxIdNumber + '\'' +
            ", address=" + getAddress() +
//            ", email='" + getEmail() + '\'' +
            '}';
    }
}
