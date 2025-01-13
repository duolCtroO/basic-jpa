package oort.cloud.shop.service;

import oort.cloud.shop.entity.Member;
import oort.cloud.shop.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long join(Member member){
        if(isDuplicateName(member)){
            throw new IllegalArgumentException("중복된 이름 입니다.");
        }
        memberRepository.save(member);
        return member.getId();
    }

    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    private boolean isDuplicateName(Member member) {
        return memberRepository.existsByName(member.getName());
    }
}
