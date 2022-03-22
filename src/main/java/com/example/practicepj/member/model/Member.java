package com.example.practicepj.member.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Member {

    @Id
    private String userId;

    private String password;
    private String userName;
    private String phone;
    private boolean adminYn;
    private LocalDateTime regDt;

}
