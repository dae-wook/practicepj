package com.example.practicepj.member.service;

import com.example.practicepj.MailComponent;
import com.example.practicepj.member.model.EmailAuth;
import com.example.practicepj.member.repository.EmailAuthRepository;
import com.example.practicepj.member.repository.MemberRepository;
import com.example.practicepj.member.model.Member;
import com.example.practicepj.member.model.MemberInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService{

    private final MailComponent mailComponent;
    private final MemberRepository memberRepository;
    private final EmailAuthRepository emailAuthRepository;

    @Override
    public boolean register(MemberInput param) {

        Optional optionalMember = memberRepository.findById(param.getUserId());

        if(optionalMember.isPresent()) {
            return false;
        }

        Member member = Member.builder()
                .userId(param.getUserId())
                .password((param.getPassword()))
                .phone(param.getPhone())
                .userName(param.getUserName())
                .regDt(LocalDateTime.now())
                .build();

        memberRepository.save(member);

        return true;
    }

    @Override
    public boolean emailAuth(String userId) {

        String authKey = "";
        for(int i = 0; i < 4; i++) {
            authKey += (int)(Math.random() *10);
        }

        EmailAuth emailAuth = EmailAuth.builder()
                .userId(userId)
                .authKey(authKey)
                .build();
        emailAuthRepository.save(emailAuth);
        String text = "<p>아래 인증코드를 인증란에 적어요</p>" + authKey;
        mailComponent.sendMail(userId,"대욱 닷컴 가입 인증 메일 입니다",text);


        return false;
    }

    @Override
    public boolean emailAuthComplete(String userId, String authKey) {

        boolean result = false;

        Optional<EmailAuth> optionalEmailAuth = emailAuthRepository.findById(userId);

        if(optionalEmailAuth.isPresent()) {
            EmailAuth emailAuth = optionalEmailAuth.get();
            System.out.println(emailAuth.toString());
            if(emailAuth.getAuthKey().equals(authKey)) {
                emailAuth.setAuthYn(true);
                emailAuthRepository.save(emailAuth);
                result = true;
            }
        }

        return result;
    }
}
