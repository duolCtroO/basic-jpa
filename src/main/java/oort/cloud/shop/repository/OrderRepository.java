package oort.cloud.shop.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import oort.cloud.shop.data.OrderSearch;
import oort.cloud.shop.entity.Order;
import oort.cloud.shop.entity.OrderStatus;
import oort.cloud.shop.entity.QMember;
import oort.cloud.shop.entity.QOrder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class OrderRepository {

    @PersistenceContext
    private EntityManager em;

    private final JPAQueryFactory jpaQueryFactory;

    public OrderRepository(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public void save(Order order){
        em.persist(order);
    }

    public Order findOne(Long id){
        return em.find(Order.class, id);
    }

    public List<Order> findAll(OrderSearch orderSearch){
        OrderStatus orderStatus = orderSearch.getOrderStatus();
        String memberName = orderSearch.getMemberName();
        QOrder order = QOrder.order;
        QMember qMember = QMember.member;
        return jpaQueryFactory.selectFrom(order)
                .innerJoin(qMember)
                .on(order.member.id.eq(qMember.id))
                .where(qMember.name.eq(memberName), order.status.eq(orderStatus))
                .fetch();
    }
}
