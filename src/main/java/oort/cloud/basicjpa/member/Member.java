package oort.cloud.basicjpa.member;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MEMBER")
public class Member {
    @Id
    private String id;
    private String name;
    private int age;
}
