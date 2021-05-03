package inflearn.spring_introduction.service;

import inflearn.spring_introduction.domain.Member;
import inflearn.spring_introduction.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


class MemberServiceeTest {

    MemoryMemberRepository memberRepository;
    MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() throws Exception {

        // Given
        Member member = new Member();
        member.setName("hello");
        member.setAddress("천안");
        member.setPhone("010-8807-6106");

        // When
        Long savedID = memberService.join(member);

        // Then
        Member findMember = memberRepository.findById(savedID).orElseThrow(() -> new NoSuchElementException());
        assertThat(findMember).isEqualTo(member);

    }

    @Test
    void 중복_회원_예외() throws Exception {

        // Given
        Member member1 = new Member();
        member1.setName("hello");
        member1.setAddress("천안");
        member1.setPhone("010-8807-6106");

        Member member2 = new Member();
        member2.setName("hello");
        member2.setAddress("천안");
        member2.setPhone("010-8807-6106");

        // When
        memberService.join(member1);

        // 예외가 발생해야 함
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));

        // Then
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }

    @Test
    void findMembers() {
        // Given
        Member member1 = new Member();
        member1.setName("hello");
        member1.setAddress("천안");
        member1.setPhone("010-8807-6106");
        memberService.join(member1);

        Member member2 = new Member();
        member2.setName("hello1");
        member2.setAddress("천안");
        member2.setPhone("010-8807-6106");
        memberService.join(member2);

        // When
        List<Member> result = memberService.findMembers();

        // Then
        assertThat(result.size()).isEqualTo(2);

    }

    @Test
    void findOne() {

        // Given
        Member member1 = new Member();
        member1.setName("hello");
        member1.setAddress("천안");
        member1.setPhone("010-8807-6106");
        memberService.join(member1);

        Member member2 = new Member();
        member2.setName("hello1");
        member2.setAddress("천안");
        member2.setPhone("010-8807-6106");
        memberService.join(member2);

        // When
        Optional<Member> result = memberService.findOne(member1.getId());

        // Then
        assertThat(result.orElseGet(() -> null).getId()).isEqualTo(member1.getId());

    }
}