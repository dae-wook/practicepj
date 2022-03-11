package com.example.practicepj.member.service;

import com.example.practicepj.member.model.MemberInput;


public interface MemberService {

    boolean register(MemberInput param);
    boolean emailAuth(String userId);
    boolean emailAuthComplete(String userId, String authKey);

}
