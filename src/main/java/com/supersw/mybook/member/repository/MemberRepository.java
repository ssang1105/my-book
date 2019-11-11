package com.supersw.mybook.member.repository;

import com.supersw.mybook.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByMemberId(String memberId);
    Member findByMemberIdAndPassword(String memberId, String password);
}