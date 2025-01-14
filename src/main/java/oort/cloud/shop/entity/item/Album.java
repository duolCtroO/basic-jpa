package oort.cloud.shop.entity.item;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("A")
public class Album extends Item {
    private String artist;
    private String etc;
}
