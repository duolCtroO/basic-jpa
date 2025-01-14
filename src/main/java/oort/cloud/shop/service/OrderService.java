package oort.cloud.shop.service;

import oort.cloud.shop.entity.*;
import oort.cloud.shop.repository.OrderRepository;
import oort.cloud.shop.data.OrderSearch;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderService {
    private final MemberService memberService;
    private final ItemService itemService;
    private final OrderRepository orderRepository;

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
        Order order = orderRepository.findOne(orderId);
        order.cancel();
    }

    public List<Order> findAll(){
        return orderRepository.findAll(new OrderSearch());
    }
}
