package oort.cloud.basicjpa.shop.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "ORDERS")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "USER_ID")
    private long userId;

    @Column(name = "ORDER_DATE")
    private LocalDateTime orderDate;

    @Column(name = "STATUS")
    private OrderStatus status;
}
