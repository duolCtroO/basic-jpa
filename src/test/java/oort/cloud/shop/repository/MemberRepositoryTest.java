package oort.cloud.shop.repository;
import jakarta.persistence.EntityManager;
import oort.cloud.shop.entity.Address;
import oort.cloud.shop.entity.Member;
import oort.cloud.shop.entity.Order;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private EntityManager em;


    @Test
    void 맴버_등록이_된다(){
        //given
        Member member = new Member();
        member.setName("test1");
        Address address = new Address();
        address.setCity("testCity");
        address.setZipcode("testZipcode");
        address.setStreet("testStreet");
        member.setAddress(address);
        Order order = new Order();
        member.setOrders(List.of(order));

        //when
        memberRepository.save(member);

        //then
        em.clear();
        List<Member> findMembers = memberRepository.findByName("test1");
        Assertions.assertThat(findMembers).hasSize(1);
        Assertions.assertThat(findMembers.get(0))
                .usingRecursiveComparison()
                .ignoringFields("orders")//지연 로드로 비교 불가
                .isEqualTo(member);
    }

}