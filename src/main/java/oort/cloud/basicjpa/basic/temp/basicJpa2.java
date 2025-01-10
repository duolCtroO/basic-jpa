package oort.cloud.basicjpa.basic.temp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import oort.cloud.basicjpa.basic.member.Member;
import oort.cloud.basicjpa.basic.member.RoleType;
import oort.cloud.basicjpa.basic.member.Team;

import java.time.LocalDateTime;

public class basicJpa2 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        Member member = new Member(1, "test1", 12, RoleType.ADMIN, LocalDateTime.now(), LocalDateTime.now(), new Team(), "test");
        Member member2 = new Member(2, "test1", 12, RoleType.ADMIN, LocalDateTime.now(), LocalDateTime.now(), new Team(), "test");
//        saveAndUpdateSuc(emf, member);
        saveAndUpdateFail(emf, member2);
    }

    private static void saveAndUpdateSuc(EntityManagerFactory emf, Member member) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            //영속컨텍스트(엔티티매니저)에 Member 객체 관리 시작
            //영속컨텍스트에서 관리하는 1차 캐시에 반영
            //영속컨텍스트에서 관리되는 쓰기 지연 SQL 저장소에 쿼리 저장
            em.persist(member);

            //영속컨텍스트에서 관리하는 1차 캐시에 반영
            //영속컨텍스트에서 관리되는 쓰기 지연 SQL 저장소에 쿼리 저장
            member.setName("test1");

            //실제 쿼리 날림
            tx.commit();
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }
    }

    private static void saveAndUpdateFail(EntityManagerFactory emf, Member member) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            //영속컨텍스트(엔티티매니저)에 Member 객체 관리 시작
            //영속컨텍스트에서 관리하는 1차 캐시에 반영
            //영속컨텍스트에서 관리되는 쓰기 지연 SQL 저장소에 쿼리 저장
            em.persist(member);

            //실제 쿼리 날림
            tx.commit();
            //영속컨텍스트에서 관리하는 1차 캐시에 반영
            //영속컨텍스트에서 관리되는 쓰기 지연 SQL 저장소에 쿼리 저장
            member.setName("test1");

        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }
    }
}
