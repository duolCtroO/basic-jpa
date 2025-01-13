package oort.cloud.basicjpa.basic.member;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class LazyAndEagerLoadingMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();
        eagerTest(em);
        findEagerTest(em);
    }

    public static void eagerTest(EntityManager em){
        Member member = new Member();
        member.setName("test1");
        em.persist(member);
        Team team = new Team();
        member.setTeam(team);
    }

    public static void findEagerTest(EntityManager em){
        Member test1 = em.find(Member.class, "1");

    }
}
