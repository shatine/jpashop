package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {
        if (item.getId() == null) {
            em.persist(item);
        } else {
            // select를 통해서 준영속상태를 영속상태로 만든 후,
            // JPA 의해서 변경감지가 발생하여, 업데이트가 실행 됨.
            em.merge(item); // findItem을 통해서 영속성 상태에서 관리되는 아이테을 반환 함.
            // 병합시 주의 : 모든필드를 업데이트 함. 값이 없을 경우, 널값으로 교체된다.
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
