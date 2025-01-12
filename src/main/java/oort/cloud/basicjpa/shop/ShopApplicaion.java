package oort.cloud.basicjpa.shop;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import oort.cloud.basicjpa.shop.entity.*;

public class ShopApplicaion {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        test(em);
        tx.commit();
    }


    public static void test(EntityManager em){
        Delivery delivery = new Delivery();
        em.persist(delivery);
    }

}
