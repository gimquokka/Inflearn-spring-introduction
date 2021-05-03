package inflearn.spring_introduction.controller;

import inflearn.spring_introduction.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private MemberService memberServicee;

    // DI MemberService Object to MemberController
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberServicee = memberService;
    }



}
