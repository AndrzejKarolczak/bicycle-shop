package com.example.bicycleshop;

import com.example.bicycleshop.backend.model.*;
import com.example.bicycleshop.backend.model.enums.BicyclePartType;
import com.example.bicycleshop.backend.model.enums.BicycleType;
import com.example.bicycleshop.backend.model.enums.OrderStatus;
import com.example.bicycleshop.security.model.Account;
import com.example.bicycleshop.security.model.AuthorityGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.math.BigDecimal;

@SpringBootApplication
public class BicycleShopApplication implements CommandLineRunner {

    @Autowired
    private EntityManagerFactory emf;

//	@Autowired
//	private AuthorityGroupRepository agr;

    public static void main(String[] args) {
        SpringApplication.run(BicycleShopApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        insertInitialData();
    }

    private void insertInitialData() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Country poland = new Country("Poland");
        em.persist(poland);

        City warszawa = new City("Warsaw", poland);
        City bydgoszcz = new City("Bydgoszcz", poland);
        em.persist(warszawa);
        em.persist(bydgoszcz);

        Address clientAddress = new Address("Marszałkowska", "34", "1", "00-444", warszawa);
        Address manufacturerAddress = new Address("Przemysłowa", "28B", "85-758", bydgoszcz);
        em.persist(clientAddress);
        em.persist(manufacturerAddress);

        AuthorityGroup authorityGroup = em.find(AuthorityGroup.class, 3L);
        Account account = new Account("a.karolczak", "a.karolczak", authorityGroup);
        account.setEnabled(true)
            .setCredentialsNonExpired(true)
            .setAccountNonExpired(true)
            .setAccountNonLocked(true);
        em.persist(account);

        Individual client = new Individual("Andrzej", "Karolczak", clientAddress, "s17896@pjwstk.edu.pl", account);
        em.persist(client);

        Organization unibike = new Organization("Unibike", manufacturerAddress, "biuro@unibike.pl", "554-008-33-57");
        em.persist(unibike);

        Product bike = new Bicycle("VIPER GTS", new BigDecimal("3199.0"), BicycleType.CROSS, unibike);
        em.persist(bike);

        Order buyBicycleOrder = new Order(client, OrderStatus.PRELIMINARY);
        em.persist(buyBicycleOrder);

        ProductInOrder bicycleInOrder = new ProductInOrder(bike, buyBicycleOrder, 1);
        bike.addProductToOrder(bicycleInOrder);
        buyBicycleOrder.addProductToOrder(bicycleInOrder);
        em.persist(bicycleInOrder);

        Product part = new BicyclePart("Unibike nie robi łańcuchów", new BigDecimal("100.0"),
            BicyclePartType.CHAIN, unibike);
        em.persist(part);

        Order buyPartOrder = new Order(client, OrderStatus.PRELIMINARY);
        em.persist(buyPartOrder);

        ProductInOrder partInOrder = new ProductInOrder(part, buyPartOrder, 2);
        part.addProductToOrder(partInOrder);
        buyPartOrder.addProductToOrder(partInOrder);
        em.persist(partInOrder);
        tx.commit();
    }
}
