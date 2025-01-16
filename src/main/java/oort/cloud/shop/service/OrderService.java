package oort.cloud.shop.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import oort.cloud.shop.entity.*;
import oort.cloud.shop.entity.item.Item;
import oort.cloud.shop.repository.OrderRepository;
import oort.cloud.shop.data.OrderSearch;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderService {
    private final MemberService memberService;
    private final ItemService itemService;
    private final OrderRepository orderRepository;

    @PersistenceContext
    private EntityManager em;

    public OrderService(MemberService memberService, ItemService itemService, OrderRepository orderRepository) {
        this.memberService = memberService;
        this.itemService = itemService;
        this.orderRepository = orderRepository;
    }

    public Long order(Long memberId, Long itemId, int itemCount){
        Member member = memberService.findOne(memberId);
        Item item = itemService.findById(itemId);
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        OrderItem orderItem = OrderItem.createOrderItem(item, itemCount, item.getPrice());
        Order order = Order.createOrder(member, delivery, orderItem);

        orderRepository.save(order);
        return order.getId();
    }

    public void cancelOrder(Long orderId){
        Optional<Order> optOrder = orderRepository.findById(orderId);
        Order order = optOrder.orElseThrow(() -> new RuntimeException("조회된 주문이 없습니다. OrderId : " + orderId));
        order.cancel();
    }

    public List<Order> findOrders(OrderSearch orderSearch){
        return orderRepository.findAll(orderSearch.toSpecification());
    }
}
