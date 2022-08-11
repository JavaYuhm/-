package com.example.springintro.service;

import com.example.springintro.domain.Member;
import com.example.springintro.repository.MemberRepository;
import com.example.springintro.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    // new가 외부에서 생성 , Dependency Injection
    @Autowired
    public MemberService(MemoryMemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
    /**
     * 회원가입
     */
    public Long join(Member member){

        // 같은 이름이 있는 중복 회원 X
        //Optional<Member> result = memberRepository.findByName(member.getName());
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m ->{
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
       return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
