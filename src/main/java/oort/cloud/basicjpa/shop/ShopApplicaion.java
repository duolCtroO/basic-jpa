package oort.cloud.basicjpa.shop;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ShopApplicaion {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop");

    }
}
