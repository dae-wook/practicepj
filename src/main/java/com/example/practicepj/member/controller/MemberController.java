package com.example.practicepj.member.controller;

import com.example.practicepj.member.model.MemberInput;
import com.example.practicepj.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/register")
    public String register() {

        return "member/register";
    }

    @PostMapping("/member/register")
    public String register(Model m, MemberInput param) {

        boolean result = memberService.register(param);
        m.addAttribute("result", result);

        return "member/register_complete";
    }

    @GetMapping("/member/email-auth")
    public String emailAuth(String userId) {

        boolean result = memberService.emailAuth(userId);

        return "index";

    }

    @GetMapping("/member/email-auth-complete")
    public String emailAuthComplete(String userId, String authKey) {

        String res = "false";
        boolean result = memberService.emailAuthComplete(userId, authKey);

        if(result)res = "true";
        if(!result)res = "false";


        return res;

    }

    @GetMapping("/member/info")
    public String memberInfo() {

        return "member/info";
    }

}
