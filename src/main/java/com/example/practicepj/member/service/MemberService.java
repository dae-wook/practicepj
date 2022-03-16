package com.example.practicepj.member.service;

import com.example.practicepj.member.model.MemberInput;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface MemberService extends UserDetailsService {

    boolean register(MemberInput param);
    boolean emailAuth(String userId);
    boolean emailAuthComplete(String userId, String authKey);

}
