package oort.cloud.basicjpa.basic.product;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;



@Data
@EqualsAndHashCode
public class MemberProductId implements Serializable {
    private Long member2;
    private Long product;
}
