package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.dto.MemberForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Member;
import com.example.firstproject.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class MemberController {
    @Autowired
    MemberRepository memberRepository;

    @GetMapping("/signup")
    public String signup() {
        return "members/new";
    }

    @PostMapping("/join")
    public String createMember(MemberForm memberForm){
        log.info(memberForm.toString());
//        System.out.println(memberForm.toString());
        //1. DTO를 엔티티로 변환
        Member member = memberForm.toEntity();
        log.info(member.toString());
//        System.out.println(member.toString());
        //2. repository로 엔티티 DB에 저장
        Member saved = memberRepository.save(member);
        log.info(saved.toString());
//        System.out.println(saved.toString());
        return "";
    }
}
