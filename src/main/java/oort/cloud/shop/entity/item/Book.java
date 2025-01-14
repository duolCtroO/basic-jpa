package oort.cloud.shop.entity.item;

import jakarta.persistence.*;
import oort.cloud.shop.entity.item.Item;

@Entity
@DiscriminatorValue("B")
public class Book extends Item {
    private String author;
    private String isbn;
}
