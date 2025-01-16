package oort.cloud.shop.data;

import lombok.Data;
import oort.cloud.shop.entity.Order;
import oort.cloud.shop.entity.OrderStatus;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.domain.Specification;

import static oort.cloud.shop.domain.OrderSpec.memberNameLike;
import static oort.cloud.shop.domain.OrderSpec.orderStatusEq;
import static org.springframework.data.jpa.domain.Specification.where;

@Data
public class OrderSearch {
    private String memberName;
    private OrderStatus orderStatus;

    public Specification<Order> toSpecification(){
        return where(memberNameLike(memberName).and(orderStatusEq(orderStatus)));
    }
}
