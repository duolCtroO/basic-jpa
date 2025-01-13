package oort.cloud.shop.entity;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Address {
    private String city;
    private String street;
    private String zipcode;
}
