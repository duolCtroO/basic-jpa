package oort.cloud.shop.data;

import lombok.Data;
import oort.cloud.shop.entity.OrderStatus;

@Data
public class OrderSearch {
    private String memberName;
    private OrderStatus orderStatus;
}
