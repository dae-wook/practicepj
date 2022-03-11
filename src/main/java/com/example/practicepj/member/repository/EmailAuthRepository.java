package com.example.practicepj.member.repository;

import com.example.practicepj.member.model.EmailAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailAuthRepository extends JpaRepository<EmailAuth, String> {
}
