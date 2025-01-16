package oort.cloud.shop.domain;

import jakarta.persistence.criteria.*;
import oort.cloud.shop.entity.Order;
import oort.cloud.shop.entity.OrderStatus;
import org.springframework.data.jpa.domain.Specification;

public class OrderSpec {

    public static Specification<Order> memberNameLike(final String memberName){
        return (root, query, criteriaBuilder) -> {
            if(memberName.isEmpty()) return null;
            Join<Object, Object> m = root.join("member", JoinType.INNER);
            return criteriaBuilder.like(m.get("name"), "%" + memberName + "%");
        };
    }

    public static Specification<Order> orderStatusEq(final OrderStatus orderStatus){
        return (root, query, criteriaBuilder) -> {
            if(orderStatus == null) return null;
            return criteriaBuilder.equal(root.get("status"), orderStatus);
        };
    }
}
