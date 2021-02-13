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
@Table(name = "organizations")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Organization extends BusinessEntity {
    @NotBlank(message = ValidationMessages.NOT_BLANK)
    @Column(nullable = false)
    private String name;

    @NotBlank(message = ValidationMessages.NOT_BLANK)
    @Column(name = "tax_id_number", nullable = false, unique = true)
    private String taxIdNumber;

    public Organization(String name, Address address, String email, String taxIdNumber) {
        super(address, email);
        this.name = name;
        this.taxIdNumber = taxIdNumber;
    }

    public Organization(String name, Address address, String email, String taxIdNumber, Account account) {
        super(address, email, account);
        this.name = name;
        this.taxIdNumber = taxIdNumber;
    }

    @Override
    public String toString() {
        return "Organization{" +
            "businessEntityId=" + getBusinessEntityId() +
            ", name='" + name + '\'' +
            ", taxIdNumber='" + taxIdNumber + '\'' +
            ", address=" + getAddress() +
            ", email='" + getEmail() + '\'' +
            '}';
    }
}
