package oort.cloud.shop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import oort.cloud.shop.entity.Member;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    public boolean existsByName(String name){
        return em.createQuery("select count(m) from Member m where m.name =:name", Long.class)
                .setParameter("name", name)
                .getSingleResult() > 0;
    }
}
