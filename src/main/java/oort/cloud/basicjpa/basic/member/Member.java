package oort.cloud.basicjpa.basic.member;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MEMBER", uniqueConstraints = @UniqueConstraint(
        name = "NAME_AGE_UNIQUE",
        columnNames = {"NAME", "AGE"}
))
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;
    @Column(name = "NAME", nullable = false, length = 10)
    private String name;

    private int age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;

    @ManyToOne(fetch = FetchType.EAGER) // 즉시 로딩 설정
//    @ManyToOne(fetch = FetchType.LAZY) // 지연 로딩
    @JoinColumn(name = "TEAM_ID")
    private Team team;


    public void setTeam(Team team){
        if(this.team != null){
            this.team.getMembers().remove(this);
        }
        this.team = team;
        this.team.getMembers().add(this);
    }

    @Lob
    private String descriptions;

}
