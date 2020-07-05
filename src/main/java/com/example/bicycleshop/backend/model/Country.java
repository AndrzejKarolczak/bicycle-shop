package com.example.bicycleshop.backend.model;

import com.example.bicycleshop.helpers.ValidationMessages;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "countries")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id", updatable = false)
    private long countryId;

    @NotBlank(message = ValidationMessages.NOT_BLANK)
    @Column(nullable = false)
    private String name;

    public Country(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Country{" +
            "name='" + name + '\'' +
            '}';
    }
}