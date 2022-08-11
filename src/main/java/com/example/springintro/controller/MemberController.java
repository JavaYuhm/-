package com.example.springintro.controller;

import com.example.springintro.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    // 인스턴스를 여러 개 만들 필요 X
    private final MemberService memberService;

   @Autowired
   public MemberController(MemberService memberService){
       this.memberService = memberService;
   }
}
