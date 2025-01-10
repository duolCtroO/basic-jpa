package oort.cloud.basicjpa.shop.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.LinkedList;
import java.util.List;

@Data
@Entity
@Table(name = "MEMBER")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STREET")
    private String street;

    @Column(name = "ZIPCODE")
    private String zipcode;

    @OneToMany(mappedBy = "member")
    @ToString.Exclude
    private List<Order> orders = new LinkedList<>();
}
