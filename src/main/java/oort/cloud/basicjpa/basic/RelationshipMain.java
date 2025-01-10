package oort.cloud.basicjpa.basic;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import oort.cloud.basicjpa.basic.member.Member;
import oort.cloud.basicjpa.basic.member.RoleType;
import oort.cloud.basicjpa.basic.member.Team;

import java.time.LocalDateTime;
import java.util.List;

public class RelationshipMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        saveMember(emf);
        findMember(emf);
        updateMember(emf);
        findJPQL(emf);
    }


    private static void saveMember(EntityManagerFactory emf){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        Team team = new Team();
        team.setTeamId(1);
        team.setName("team1");
        em.persist(team);

        Team team2 = new Team();
        team2.setTeamId(2);
        team2.setName("team2");
        em.persist(team2);

        Member member = new Member();
        member.setAge(12);
        member.setName("mem1");
        member.setTeam(team);
        em.persist(member);

        Member member2 = new Member();
        member2.setAge(12);
        member2.setName("mem2");
        member2.setTeam(team);
        em.persist(member2);

        Member member3 = new Member();
        member3.setAge(12);
        member3.setName("mem3");
        member3.setTeam(team2);
        em.persist(member3);

        tx.commit();
    }

    private static void findMember(EntityManagerFactory emf){
        EntityManager em = emf.createEntityManager();
        Member mem1 = em.find(Member.class, "1");
        Team team = mem1.getTeam();
        String teamName = team.getName();
        System.out.println(teamName);
    }

    private static void updateMember(EntityManagerFactory emf){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Member mem1 = em.find(Member.class, "1");
        Team team2 = em.find(Team.class, 2);
        mem1.setTeam(team2);
        tx.commit();
    }

    private static void deleteTeam(EntityManagerFactory emf){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Member mem1 = em.find(Member.class, "1");
        mem1.setTeam(null);
        tx.commit();
    }

    private static void findJPQL(EntityManagerFactory emf){
        EntityManager em = emf.createEntityManager();

        String jpql = "SELECT m FROM Member m JOIN m.team t WHERE " + "t.name=:teamName";
        List<Member> memberList = em.createQuery(jpql, Member.class).setParameter("teamName", "team1").getResultList();
        for (Member member : memberList) {
            System.out.println(member);
        }
    }
}
