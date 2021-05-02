package inflearn.spring_introduction.repository;

import inflearn.spring_introduction.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    Optional<Member> findByPhone(String phone);
    List<Member> findAll();

}
