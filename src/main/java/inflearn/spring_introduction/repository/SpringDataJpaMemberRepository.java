package inflearn.spring_introduction.repository;

import inflearn.spring_introduction.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    Optional<Member> findByName(String name);
    Optional<Member> findByPhone(String phone);

}
