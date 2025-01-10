package oort.cloud.basicjpa.shop.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
@Entity
@Table(name = "ORDER_ITEM")
public class OrderItem {
    @Id
    @GeneratedValue
    @Column(name = "ORDER_ITEM_ID")
    private long orderItemId;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    @Column(name = "ORDER_PRICE")
    private int orderPrice;

    @Column(name = "COUNT")
    private int count;
}
