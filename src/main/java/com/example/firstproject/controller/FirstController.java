package com.example.firstproject.controller;
// 컨트롤러 선언과 동시에 자동으로 임포트
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class FirstController {

    @GetMapping("/hi")
    public String niceToMeetYou(Model model) {
        model.addAttribute("username", "수빈");
        return "greetings"; // greetings.mustache 파일 반환
    }

    @GetMapping("/bye")
    public String SeeYouNext(Model model) { // 객체 받아오기
        model.addAttribute("nickname", "개똥");
        return "goodbye";
    }

}
