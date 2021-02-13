package com.example.bicycleshop.backend.entities;

import com.example.bicycleshop.validation.ValidationMessages;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cities")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id", updatable = false)
    private long cityId;

    @NotBlank(message = ValidationMessages.NOT_BLANK)
    @Column(nullable = false)
    private String name;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},
        fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},
        fetch = FetchType.LAZY, mappedBy = "city")
    private List<Address> addresses = new ArrayList<>();

    @Column
    private BigDecimal longitude;

    @Column
    private BigDecimal latitude;

    public City(String name, Country country) {
        this.name = name;
        this.country = country;
    }

    public City(String name, Country country, BigDecimal longitude, BigDecimal latitude) {
        this.name = name;
        this.country = country;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public void addAddress(Address address){
        if(Objects.nonNull(address))
            addresses.add(address);
    }

    public void removeAddress(Address address){
        if(Objects.nonNull(address))
            addresses.remove(address);
    }

    @Override
    public String toString() {
        return "City{" +
            "name='" + name + '\'' +
            ", country=" + country +
            '}';
    }
}
