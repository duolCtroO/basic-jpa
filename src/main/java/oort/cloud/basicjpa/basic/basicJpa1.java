package oort.cloud.basicjpa.basic;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import oort.cloud.basicjpa.member.Member;

import java.util.List;

public class basicJpa1 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            logic(em);
            tx.commit();
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }
        emf.close();
    }

    public static void logic(EntityManager em){
        String id = "id1";
        Member member = new Member();
        member.setId(id);
        member.setName("하이");
        member.setAge(21);

        em.persist(member);

        member.setAge(14);

        Member findMember = em.find(Member.class, id);

        System.out.println("findMember = " + findMember);

        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();

        System.out.println("member.size = " + members.size());
    }
}
