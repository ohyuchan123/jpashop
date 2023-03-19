package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
/*DI(의존성 주입)의 방법 중에 생성자 주입을 임의의 코드없이
자동으로 설정해주는 어노테이션이다*/
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item){
        //item은 jdbc에 저장하기 전 까지 id값이 없다
        if(item.getId() == null){
            em.persist(item);
        }else{
            em.merge(item);
        }
    }

    public Item findOne(Long id){
        return em.find(Item.class,id);
    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item i",Item.class)
                .getResultList();
    }
}
