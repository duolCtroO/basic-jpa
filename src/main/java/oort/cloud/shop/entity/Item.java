package oort.cloud.shop.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "STOCK_QUANTITY")
    private int stockQuantity;

    public void addStock(int count){
        setStockQuantity(getStockQuantity() + count);
    }

    public void removeStock(int count){
        int restStock = getStockQuantity() - count;
        if(restStock < 0) throw new RuntimeException("재고가 부족합니다.");
        this.stockQuantity = restStock;
    }

}
