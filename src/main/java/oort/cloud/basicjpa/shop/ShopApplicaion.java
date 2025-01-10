package oort.cloud.basicjpa.shop;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import oort.cloud.basicjpa.shop.entity.Item;
import oort.cloud.basicjpa.shop.entity.Member;
import oort.cloud.basicjpa.shop.entity.Order;
import oort.cloud.basicjpa.shop.entity.OrderItem;

import java.util.List;

public class ShopApplicaion {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop");
        EntityManager em = emf.createEntityManager();
        createTest(em);
        findTest(em);
    }

    public static void createTest(EntityManager em){
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Member member = new Member();
        member.setName("test1");
        em.persist(member);

        Item item = new Item();
        item.setName("test1");
        em.persist(item);

        Order order = new Order();
        order.setMember(member);

        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setItem(item);
        em.persist(orderItem);

        order.addOrderItems(orderItem);
        em.persist(order);

        tx.commit();
    }

    public static void findTest(EntityManager em){
        Member member = em.find(Member.class, "1");
        List<Order> orders = member.getOrders();
        System.out.println(orders);

        Order order = em.find(Order.class, "1");
        System.out.println(order);
        List<OrderItem> orderItems = order.getOrderItems();
        System.out.println(orderItems);
    }
}
