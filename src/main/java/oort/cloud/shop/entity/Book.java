package oort.cloud.shop.entity;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("B")
public class Book extends Item{
    private String author;
    private String isbn;
}