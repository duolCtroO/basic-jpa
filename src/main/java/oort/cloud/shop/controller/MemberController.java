package oort.cloud.shop.controller;


import jakarta.validation.Valid;
import oort.cloud.shop.entity.Member;
import oort.cloud.shop.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity join(@RequestBody @Valid Member member){
        memberService.join(member);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("회원 가입 성공");
    }
}
