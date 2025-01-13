package oort.cloud.shop.repository;

import jakarta.persistence.EntityManager;
import oort.cloud.shop.entity.Item;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemRepositoryTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private ItemRepository itemRepository;


    @Test
    void 상품이_새롭게_저장되고_수정된다(){
        //given
        Item item = new Item();
        item.setName("test1");
        item.setPrice(1000);
        item.setStockQuantity(10);

        //when
        itemRepository.save(item);

        //then
        em.clear();
        Item findItem = em.find(Item.class, 1);
        Assertions.assertThat(item).usingRecursiveComparison().isEqualTo(findItem);

        //and
        findItem.setName("test2");
        itemRepository.save(findItem);

        //then
        em.clear();
        Item modifyItem = em.find(Item.class, findItem.getId());
        Assertions.assertThat(findItem).usingRecursiveComparison().isEqualTo(modifyItem);
    }

}