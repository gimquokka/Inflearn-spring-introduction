package inflearn.YounghanKimspringintroduction.repository;

import inflearn.YounghanKimspringintroduction.domain.Member;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static AtomicLong orderIdGenerator = new AtomicLong(0);

    @Override
    public Member save(Member member) {
        member.setId(orderIdGenerator.getAndIncrement());
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();//반환 타입이 이미 Optional 임
    }

    // findByName을 응용하여 만든 findByPhone
    @Override
    public Optional<Member> findByPhone(String phone) {
        return store.values().stream()
                .filter(member -> member.getPhone().equals(phone))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
