package oort.cloud.basicjpa.basic.member;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.LinkedList;
import java.util.List;

@Entity
@Data
public class Team {
    @Id
    @Column(name = "TEAM_ID")
    private int teamId;

    private String name;

    @OneToMany(mappedBy = "team")
    @ToString.Exclude
    private List<Member> members = new LinkedList<>();
}
