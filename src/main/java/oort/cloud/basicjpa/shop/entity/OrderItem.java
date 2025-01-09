package oort.cloud.basicjpa.shop.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ORDER_ITEM")
public class OrderItem {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ORDER_ID")
    private long orderId;

    @Column(name = "ITEM_ID")
    private long itemId;

    @Column(name = "ORDER_PRICE")
    private int orderPrice;

    @Column(name = "COUNT")
    private int count;
}
