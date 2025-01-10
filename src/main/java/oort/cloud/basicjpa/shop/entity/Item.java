package oort.cloud.basicjpa.shop.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
@Entity
@Table(name = "ITEM")
public class Item {

    @Id
    @GeneratedValue
    @Column(name = "ITEM_ID")
    private long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private int price;

    @Column(name = "STOCK_QUANTITY")
    private int stockQuantity;
}
