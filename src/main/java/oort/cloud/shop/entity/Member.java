package oort.cloud.shop.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.LinkedList;
import java.util.List;

@Data
@Entity(name = "MEMBER_SHOP")
@Table(name = "MEMBER_SHOP")
@EqualsAndHashCode(callSuper = false)
public class Member extends CommonEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    @ToString.Exclude
    private List<Order> orders = new LinkedList<>();
}
