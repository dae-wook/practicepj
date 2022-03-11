package com.example.practicepj.member.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberInput {

    private String userId;
    private String password;
    private String phone;
    private String userName;

}
