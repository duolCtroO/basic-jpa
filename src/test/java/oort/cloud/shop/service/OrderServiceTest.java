package oort.cloud.shop.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import oort.cloud.shop.entity.Item;
import oort.cloud.shop.entity.Member;
import oort.cloud.shop.entity.Order;
import oort.cloud.shop.entity.OrderStatus;
import oort.cloud.shop.repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @PersistenceContext
    EntityManager entityManager;


    @Test
    void 회원_주문이_등록된다(){
        //given
        Item item = new Item();
        item.setName("computer");
        item.setStockQuantity(10);
        item.setPrice(BigDecimal.valueOf(10000));

        Member member = new Member();
        member.setName("test1");

        //when
        entityManager.persist(member);
        entityManager.persist(item);
        Long orderId = orderService.order(member.getId(), item.getId(), 5);
        //then
        entityManager.clear();
        Order order = orderRepository.findOne(orderId);

        assertThat(order.getTotalPrice()).isEqualTo(BigDecimal.valueOf(50000).setScale(2));
        assertThat(item.getStockQuantity()).isEqualTo(5);
        assertThat(order.getOrderItems().size()).isEqualTo(1);
    }

    @Test
    void 재고를_초과하는_주문시_예외발생(){
        //given
        Item item = new Item();
        item.setName("computer");
        item.setStockQuantity(10);
        item.setPrice(BigDecimal.valueOf(10000));

        Member member = new Member();
        member.setName("test1");

        //when
        entityManager.persist(member);
        entityManager.persist(item);

        //then
        assertThrows(RuntimeException.class, () -> orderService.order(member.getId(), item.getId(), 11))
        ;
    }


    @Test
    void 주문_취소_테스트(){
        //given
        Item item = new Item();
        item.setName("computer");
        item.setStockQuantity(10);
        item.setPrice(BigDecimal.valueOf(10000));

        Member member = new Member();
        member.setName("test1");

        //when
        entityManager.persist(member);
        entityManager.persist(item);
        Long orderId = orderService.order(member.getId(), item.getId(), 2);
        orderService.cancelOrder(orderId);

        //then
        entityManager.clear();
        Order findOrder = orderRepository.findOne(orderId);
        assertThat(findOrder.getStatus()).isEqualTo(OrderStatus.CANCEL);
        assertThat(findOrder.getOrderItems().get(0).getItem().getStockQuantity()).isEqualTo(10);
    }
}