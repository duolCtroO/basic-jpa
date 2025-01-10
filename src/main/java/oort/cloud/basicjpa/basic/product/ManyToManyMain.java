package oort.cloud.basicjpa.basic.product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import oort.cloud.basicjpa.shop.entity.Member;

import java.util.List;

public class ManyToManyMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        saveTest2(em);
        tx.commit();
//        findTest(em);
        findTest2(em);
    }


    private static void saveTest(EntityManager em){
        Product product = new Product();
        product.setName("product1");

        Member2 memeber2 = new Member2();
        memeber2.setName("test1");
        memeber2.getProducts().add(product);
        product.getMember2s().add(memeber2);
        em.persist(product);
        em.persist(memeber2);
    }
    private static void saveTest2(EntityManager em){
        Product product = new Product();
        product.setName("product1");
        em.persist(product);

        Member2 memeber2 = new Member2();
        memeber2.setName("test1");
        em.persist(memeber2);

        MemberProduct memberProduct = new MemberProduct();
        memberProduct.setProduct(product);
        memberProduct.setMember2(memeber2);
        memberProduct.setOrderAmount(2);
        em.persist(memberProduct);
    }

    private static void findTest(EntityManager em){
        Member2 member2 = em.find(Member2.class, "1");
        List<Product> products = member2.getProducts();
        System.out.println(products.get(0).getName());

        Product product = em.find(Product.class, "1");
        String name = product.getMember2s().get(0).getName();
        System.out.println(name);
    }

    private static void findTest2(EntityManager em){
        MemberProductId memberProductId = new MemberProductId();
        memberProductId.setMember2(1L);
        memberProductId.setProduct(1L);

        MemberProduct memberProduct = em.find(MemberProduct.class, memberProductId);
        Product product = memberProduct.getProduct();
        Member2 member2 = memberProduct.getMember2();

        System.out.println(product.getName());
        System.out.println(member2.getName());

    }
}
