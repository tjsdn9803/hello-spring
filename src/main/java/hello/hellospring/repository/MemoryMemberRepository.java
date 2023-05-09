package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;


public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        member.setUserAuth("active");
        member.setAppendDate("2023-05-09");
        member.setUpdateDate("2023-05-09");
        System.out.println("member.getName() = " + member.getName());
        System.out.println("member.getName() = " + member.getId());
        System.out.println("member.getName() = " + member.getPassword());
        System.out.println("member.getName() = " + member.getIndex());
        System.out.println("member.getName() = " + member.getEmail());
        System.out.println("member.getName() = " + member.getNickname());
        System.out.println("member.getName() = " + member.getUserAuth());
        System.out.println("member.getName() = " + member.getAppendDate());
        System.out.println("member.getName() = " + member.getUpdateDate());
        store.put(member.getIndex(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        return store.values().stream()
                .filter(member -> member.getName().equals(email))
                .findAny();
    }

    @Override
    public Optional<Member> findByNickname(String nickname) {
        return store.values().stream()
                .filter(member -> member.getName().equals(nickname))
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
