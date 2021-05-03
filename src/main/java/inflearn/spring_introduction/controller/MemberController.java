package inflearn.spring_introduction.controller;

import inflearn.spring_introduction.domain.Member;
import inflearn.spring_introduction.domain.MemberForm;
import inflearn.spring_introduction.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private MemberService memberServicee;

    // DI MemberService Object to MemberController
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberServicee = memberService;
    }

    @GetMapping(value = "/members/new")
    public String createMemberForm() {
        return "createMemberForm";
    }

    @PostMapping(value = "/members/new")
    public String create(MemberForm form) {

        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(form.getAddress());
        member.setPhone(form.getPhone());

        memberServicee.join(member);

        return "redirect:/";
    }

    @GetMapping(value = "/members")
    public String list(Model model) {
        List<Member> members = memberServicee.findMembers();

        model.addAttribute("members", members);

        return "memberList";
    }
}
