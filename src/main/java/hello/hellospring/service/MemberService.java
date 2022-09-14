package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    } // 외부에서 넣어줄수 있도록

    /*
    * 회원가입
    * */
    public Long join(Member member){
        // 같은 이름이 있는 중복회원은 금지
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
        //result.get() 혹은 orElseget()
            .ifPresent(m ->{
                throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    /*
    * 전체회원 조회
    * */
    public List<Member> findMember() {
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}