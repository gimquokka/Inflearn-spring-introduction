package inflearn.YounghanKimspringintroduction.repository;

import inflearn.YounghanKimspringintroduction.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    void save() {
        //Given
        Member member = new Member();
        member.setName("spring");
        member.setAddress("천안");
        member.setPhone("010-8807-6106");

        Member member1 = new Member();
        member.setName("spring1");
        member.setAddress("서울");
        member.setPhone("010-8807-6106");

        //When
        repository.save(member);

        //Then
        Member result = repository.findById(member.getId()).orElseThrow(() -> new NoSuchElementException());
        assertThat(result).isEqualTo(member);

    }

    @Test
    void findById() {

        // Given
        Member member1 = new Member();
        member1.setName("spring1");
        member1.setAddress("서울");
        member1.setPhone("010-8807-6106");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        member2.setAddress("천안");
        member2.setPhone("010-8807-6106");
        repository.save(member2);


        // When
        Member result = repository.findById(member1.getId()).orElseThrow(() -> new NoSuchElementException());

        // Then
        assertThat(result).isEqualTo(member1);
    }

    @Test
    void findByName() {

        //Given
        Member member1 = new Member();
        member1.setName("spring1");
        member1.setAddress("서울");
        member1.setPhone("010-8807-6106");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        member2.setAddress("천안");
        member2.setPhone("010-8807-6106");
        repository.save(member2);


        //When
        Member result = repository.findByName(member1.getName()).orElseThrow(() -> new NoSuchElementException());


        //Then
        assertThat(result).isEqualTo(member1);
    }

    @Test
    void findByPhone() {

        //Given
        Member member1 = new Member();
        member1.setName("spring1");
        member1.setAddress("서울");
        member1.setPhone("010-8807-6106");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        member2.setAddress("천안");
        member2.setPhone("010-8807-6106");
        repository.save(member2);


        //When
        Member result = repository.findByPhone(member1.getPhone()).orElseThrow(() -> new NoSuchElementException());


        //Then
        assertThat(result).isEqualTo(member1);
    }

    @Test
    void findAll() {
        //Given
        Member member1 = new Member();
        member1.setName("spring1");
        member1.setAddress("서울");
        member1.setPhone("010-8807-6106");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        member2.setAddress("천안");
        member2.setPhone("010-8807-6106");
        repository.save(member2);


        //When
        List<Member> result = repository.findAll();


        //Then
        assertThat(result.size()).isEqualTo(2);
    }
}