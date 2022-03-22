package com.example.practicepj.member.service;

import com.example.practicepj.component.MailComponent;
import com.example.practicepj.member.model.EmailAuth;
import com.example.practicepj.member.repository.EmailAuthRepository;
import com.example.practicepj.member.repository.MemberRepository;
import com.example.practicepj.member.model.Member;
import com.example.practicepj.member.model.MemberInput;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        String encPassword = BCrypt.hashpw(param.getPassword(), BCrypt.gensalt());

        Member member = Member.builder()
                .userId(param.getUserId())
                .password((encPassword))
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Member> optionalMember = memberRepository.findById(username);

        if(!optionalMember.isPresent()) {
            throw new UsernameNotFoundException(".회원 정보가 존재하지 않습니다.");
        }

        Member member = optionalMember.get();

        List<GrantedAuthority> grantedAuthorityList = new ArrayList();
        if(member.isAdminYn()) {
            grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }else {
            grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        return new User(member.getUserId(), member.getPassword(), grantedAuthorityList);
    }
}
