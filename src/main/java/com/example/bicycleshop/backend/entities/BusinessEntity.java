package com.example.bicycleshop.backend.entities;

import com.example.bicycleshop.validation.ValidationMessages;
import com.example.bicycleshop.security.entities.Account;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "business_entities")
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public abstract class BusinessEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "business_entity_id", updatable = false)
    private long businessEntityId;

    @Email
    @NotNull(message = ValidationMessages.NOT_NULL)
    @Column(nullable = false)
    private String email;
    
    @NotNull(message = ValidationMessages.NOT_NULL)
    @Column(nullable = false)
    private String phone;

    @NotNull(message = ValidationMessages.NOT_NULL)
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},
        fetch = FetchType.LAZY)
    @JoinColumn(name = "billing_address_id", unique = true, nullable = false)
    private Address billingAddress;
    
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "shipping_address_id", unique = true)
    private Address shippingAddress;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},
        fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", unique = true)
    private Account account;

    @Setter(AccessLevel.NONE)
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},
        mappedBy = "client", fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();

    public BusinessEntity(Address billingAddress, Address shippingAddress, String email, String phone) {
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
        this.email = email;
        this.phone = phone;
    }

    public BusinessEntity(Address billingAddress, Address shippingAddress, String email, String phone, Account account) {
        this(billingAddress, shippingAddress, email, phone);
        this.account = account;
    }

    public void addOrder(Order order) {
        if (Objects.nonNull(order))
            orders.add(order);
    }

    public void removeOrder(Order order) {
        if (Objects.nonNull(order))
            orders.remove(order);
    }

    public boolean isRegisteredClient() {
        return Objects.nonNull(account);
    }
    
    public abstract String getName();
    
    public boolean isIndividual(){
        return this.getClass().equals(Individual.class);
    }
}
