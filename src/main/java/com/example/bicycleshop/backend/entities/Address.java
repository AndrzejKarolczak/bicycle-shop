package com.example.bicycleshop.backend.entities;

import com.example.bicycleshop.validation.ValidationMessages;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "addresses")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id", updatable = false)
    private long addressId;

    @NotBlank(message = ValidationMessages.NOT_BLANK)
    @Column(name = "street_name", nullable = false)
    private String streetName;

    @NotBlank(message = ValidationMessages.NOT_BLANK)
    @Column(name = "building_number", nullable = false)
    private String buildingNumber;

    @Column(name = "suite_number")
    private String suiteNumber;

    @NotBlank(message = ValidationMessages.NOT_BLANK)
    @Column(name = "postal_code", nullable = false)
    private String postalCode;

    @NotNull(message = ValidationMessages.NOT_NULL)
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},
        fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @OneToOne(mappedBy = "address", fetch = FetchType.LAZY,
        cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private BusinessEntity businessEntity;

    public Address(String streetName, String buildingNumber, String postalCode, City city) {
        this.streetName = streetName;
        this.buildingNumber = buildingNumber;
        this.postalCode = postalCode;
        this.city = city;
    }

    public Address(String streetName, String buildingNumber, String suiteNumber, String postalCode, City city) {
        this.streetName = streetName;
        this.buildingNumber = buildingNumber;
        this.suiteNumber = suiteNumber;
        this.postalCode = postalCode;
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{" +
            "streetName='" + streetName + '\'' +
            ", buildingNumber=" + buildingNumber +
            ", suiteNumber=" + suiteNumber +
            ", postalCode='" + postalCode + '\'' +
            ", city=" + city +
            '}';
    }
}
