package jpabook.jpashop.repository;

import jpabook.jpashop.Member;
import jpabook.jpashop.domain.Member_DM;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository //스프링 빈으로 등록, JPA 예외를 스프링 기반 예외로 예외 변환
@RequiredArgsConstructor
public class MemberRepository {

    private EntityManager em;

    public void save(Member_DM member){
        em.persist(member);
    }

    public Member_DM findOne(Long id){
        Member_DM member = em.find(Member_DM.class, id);
        return member;
    }

    public List<Member_DM> findAll(){

        return em.createQuery("select m from Member_DM m", Member_DM.class)
                .getResultList();
    }

    public List<Member_DM> findByName(String name){
        return em.createQuery("select m from Member_DM m where m.name= :name",Member_DM.class)
                .setParameter("name",name)
                .getResultList();
    }

}
