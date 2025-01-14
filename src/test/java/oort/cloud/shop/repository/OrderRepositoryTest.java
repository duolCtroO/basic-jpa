package oort.cloud.shop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import oort.cloud.shop.entity.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderRepositoryTest {

    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    OrderRepository orderRepository;

    @Test
    void 회원_주문이_등록된다(){
        //given
        Member member = new Member();
        member.setName("test1");
        Delivery delivery = new Delivery();
        OrderItem orderItem = OrderItem.createOrderItem(new Item(), 1, BigDecimal.valueOf(1000));
        Order order = Order.createOrder(member, delivery, orderItem);

        //when
        entityManager.persist(member);
        orderRepository.save(order);

        //then
        entityManager.clear();
        Order savedOrder = orderRepository.findOne(1L);
        assertThat(savedOrder).isNotNull();
    }
}