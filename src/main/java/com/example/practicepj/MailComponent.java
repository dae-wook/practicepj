package com.example.practicepj;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

@RequiredArgsConstructor
@Component
public class MailComponent {

    private final JavaMailSender javaMailSender;

    public boolean sendMail(String email, String subject, String text) {
        boolean result = false;

        MimeMessagePreparator mimeMessagePreparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {

                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true, "UTF-8");
                mimeMessageHelper.setTo(email);
                mimeMessageHelper.setSubject(subject);
                mimeMessageHelper.setText(text,true);
            }
        };
        try{
            javaMailSender.send(mimeMessagePreparator);
            result = true;
        }catch (Exception e) {
            e.printStackTrace();
        }



        return result;
    }

}
