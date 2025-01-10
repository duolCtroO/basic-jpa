package oort.cloud.basicjpa.basic.temp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import oort.cloud.basicjpa.basic.member.Member;
import oort.cloud.basicjpa.basic.member.RoleType;
import oort.cloud.basicjpa.basic.member.Team;

import java.time.LocalDateTime;

public class MergeJpa {

    public static void main(String[] args) {
        Member member = new Member(1, "test1", 12, RoleType.ADMIN, LocalDateTime.now(), LocalDateTime.now(), new Team(),"test");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        createMember(emf, member);
        member.setName("test2"); //member 객체 준영속이라 반영 불가능
        mergeMember(emf, member); //merge 수행 후 준영속 -> 영속 상태로 변경 반영 가능
    }

    private static void mergeMember(EntityManagerFactory emf, Member member) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
//            Member mergeMember = em.merge(member); //영속 상태인 새로운 Member 객체 생성
            member = em.merge(member); //이런식으로 참조 바꿔줌
            tx.commit();
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }
    }

    private static void createMember(EntityManagerFactory emf, Member member) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(member);
            tx.commit();
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }
    }
}
