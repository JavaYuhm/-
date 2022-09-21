package com.example.springintro.controller;

import com.example.springintro.domain.Member;
import com.example.springintro.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// @Controller, Service 등의 컴포넌트 어노테이션은 Bean으로 등록, 객체를 생성함. (Component Scan)
public class MemberController {
    // 인스턴스를 여러 개 만들 필요 X
    private final MemberService memberService;

    // 의존관계 설정
   public MemberController(MemberService memberService){
       this.memberService = memberService;
   }


   @GetMapping("/members/new")
   public String createFrom(){
       return "templates/members/createForm";
   }

    @PostMapping("/members/new")
    public String createFrom(MemberForm memberForm){
       Member member = new Member();
       member.setName(memberForm.getName());
       memberService.join(member);

       return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
       List<Member> members = memberService.findMembers();
       model.addAttribute("members", members);
       return "templates/members/memberList";
    }
}
