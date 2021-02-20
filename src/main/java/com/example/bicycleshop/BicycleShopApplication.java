package com.example.bicycleshop;

import com.example.bicycleshop.backend.entities.*;
import com.example.bicycleshop.backend.entities.enums.BicyclePartType;
import com.example.bicycleshop.backend.entities.enums.BicycleType;
import com.example.bicycleshop.backend.entities.enums.OrderStatus;
import com.example.bicycleshop.security.Roles;
import com.example.bicycleshop.security.entities.Account;
import com.example.bicycleshop.security.entities.AuthorityGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.math.BigDecimal;

@SpringBootApplication
public class BicycleShopApplication extends  SpringBootServletInitializer implements CommandLineRunner {

    @Autowired
    private EntityManagerFactory emf;

    @Autowired
    PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(BicycleShopApplication.class, args);
    }

    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
                return application.sources(BicycleShopApplication.class);
    }
    
    @Override
    public void run(String... args) throws Exception {
        insertInitialData();
    }

    private void insertInitialData() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        
        Country poland = em.find(Country.class, 136L);

        City warszawa = new City("WARSAW", poland);
        City bydgoszcz = new City("BYDGOSZCZ", poland);
        City zerniki = new City("ŻERNIKI", poland);
        em.persist(warszawa);
        em.persist(bydgoszcz);
        em.persist(zerniki);

        Address clientAddress = new Address("Marszałkowska", "34", "1", "00-444", warszawa);
        Address unibikeAddress = new Address("Przemysłowa", "28B", "85-758", bydgoszcz);
        Address shimanoAddress = new Address("Gutenberga", "9", "62-023 Gądki", zerniki);
        em.persist(clientAddress);
        em.persist(unibikeAddress);
        em.persist(shimanoAddress);

        AuthorityGroup authorityGroup = em.find(AuthorityGroup.class, Roles.CLIENT.getAuthorityGroupNumber());
        Account account = new Account("a.karolczak", passwordEncoder.encode("a.karolczak"), authorityGroup);
        account.setEnabled(true)
            .setCredentialsNonExpired(true)
            .setAccountNonExpired(true)
            .setAccountNonLocked(true);
        em.persist(account);

        Individual client = new Individual("Andrzej", "Karolczak", clientAddress, clientAddress, "s17896@pjwstk.edu.pl", "666-666-666", account);
        em.persist(client);

        Organization unibike = new Organization("Unibike", unibikeAddress, unibikeAddress, "biuro@unibike.pl", "554-008-33-57", "777-777-777");
        em.persist(unibike);
    
        BigDecimal viperPrice  = new BigDecimal("3499.0");
        Product viper = new Bicycle("VIPER GTS", viperPrice, BicycleType.CROSS, unibike);
        viper.setLinkToPicture("https://www.unibike.pl/cross/vipergts/vipergtsc_main.jpg");
        em.persist(viper);
        
        Product zetos = new Bicycle("ZETOS GTS", new BigDecimal("3979.00"), BicycleType.CROSS, unibike);
        zetos.setLinkToPicture("https://www.unibike.pl/cross/zethosgts/zethosgts_main.jpg");
        em.persist(zetos);

        Order buyBicycleOrder = new Order(client, OrderStatus.PRELIMINARY);
        em.persist(buyBicycleOrder);

        ProductInOrder bicycleInOrder = new ProductInOrder(viper, buyBicycleOrder, 1, viperPrice);
        viper.addProductToOrder(bicycleInOrder);
        buyBicycleOrder.addProductToOrder(bicycleInOrder);
        em.persist(bicycleInOrder);

        Organization shimano = new Organization("Shimano Polska", shimanoAddress, shimanoAddress, "biuro@shimano.pl",
            "554-008-33-56", "888-888-888");
        em.persist(shimano);
        
        BigDecimal brakePrice = new BigDecimal("110.99");
        Product brake = new BicyclePart("SHIMANO Acera/Altus BR-MT200 rear disc brake caliper", brakePrice,
            BicyclePartType.REAR_BRAKE, shimano);
        brake.setLinkToPicture("https://www.rosebikes.pl/images/-5ZkExzBAP_V0PbRxyjq3IJQvH80domuOqD34s_0fuk/resize:fit:1800:1200:1/gravity:no/aHR0cHM6Ly9pbWFnZXMucm9zZWJpa2VzLmRlL2dldF9pbWFnZS8_dD1EMEI0MTAzQTM4Qjk2MkZBRTZGRDU4NTU5M0REQkVCRQ.jpg");
        em.persist(brake);
    
        BigDecimal chainPrice = new BigDecimal("100.99");
        Product chain = new BicyclePart("SHIMANO DEORE XT CN-M8100 HG 12 SPEED CHAIN", chainPrice,
            BicyclePartType.CHAIN, shimano);
        chain.setLinkToPicture("https://www.rosebikes.pl/images/fEuV7EQhFBnPYR5rOfATwPJYTHVd3UC02fy_VYyP2z8/resize:fit:1800:1200:1/gravity:no/background:ffffff/aHR0cHM6Ly9pbWFnZXMucm9zZWJpa2VzLmRlL2dldF9pbWFnZS8_dD01Njg4Qzg3NTI4REExODY1MjQyMTE3RThGRkU5ODlDMw.jpeg");
        em.persist(chain);

        Order buyPartOrder = new Order(client, OrderStatus.PRELIMINARY);
        em.persist(buyPartOrder);

        ProductInOrder chainInOrder = new ProductInOrder(chain, buyPartOrder, 2, chainPrice);
        chain.addProductToOrder(chainInOrder);
        buyPartOrder.addProductToOrder(chainInOrder);
        em.persist(chainInOrder);
    
        ProductInOrder brakeInOrder = new ProductInOrder(brake, buyPartOrder, 1, brakePrice);
        brake.addProductToOrder(brakeInOrder);
        buyPartOrder.addProductToOrder(brakeInOrder);
        em.persist(brakeInOrder);
        
        tx.commit();
    }
}
