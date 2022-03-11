package com.example.practicepj.member.repository;

import com.example.practicepj.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
}
