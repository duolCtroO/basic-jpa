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
        createCategoryTest(em);
        createDeliveryTest(em);
        tx.commit();
        findCategory(em);
        findDelivery(em);
    }

    public static void createCategoryTest(EntityManager em){
        Category category = new Category();
        category.setName("category1");
        em.persist(category);

        Item item = new Item();
        item.setName("item1");
        em.persist(item);

        CategoryItem categoryItem = new CategoryItem();
        categoryItem.setCategory(category);
        categoryItem.setItem(item);
        em.persist(categoryItem);
    }

    public static void createDeliveryTest(EntityManager em){
        Delivery delivery = new Delivery();
        delivery.setDeliveryId(1L);
        delivery.setStatus(DeliveryStatus.READY);
        em.persist(delivery);

        Order order = new Order();
        order.setDelivery(delivery);
        em.persist(order);
    }

    public static void findCategory(EntityManager em){
        CategoryItem categoryItem = em.find(CategoryItem.class, 1);
        Category category = categoryItem.getCategory();
        Item item = categoryItem.getItem();

        System.out.println(category.getName());
        System.out.println(item.getName());
    }
    public static void findDelivery(EntityManager em){
        Order order = em.find(Order.class, 1);
        Delivery delivery = order.getDelivery();
        System.out.println(delivery.getStatus());
    }
}
