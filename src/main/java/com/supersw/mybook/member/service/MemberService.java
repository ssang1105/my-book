package com.supersw.mybook.member.service;

import com.supersw.mybook.error.exceptions.AlreadyExistMemberException;
import com.supersw.mybook.error.exceptions.NotExistMemberException;
import com.supersw.mybook.member.domain.Member;
import com.supersw.mybook.member.repository.MemberRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public Member join(@NonNull String id, @NonNull String password) {
        if (memberRepository.findByMemberId(id) != null) {
            throw new AlreadyExistMemberException("이미 존재하는 회원 아이디입니다. 회원 아이디 : " + id);
        }

        Member member = Member.create(id, Base64Utils.encodeToString(password.getBytes()));
        return memberRepository.save(member);
    }

    public Member login(@NonNull String id, @NonNull String password) {
        Member member = memberRepository.findByMemberIdAndPassword(id, Base64Utils.encodeToString(password.getBytes()));
        if (member == null) {
            throw new NotExistMemberException("존재하지 않는 회원입니다. 회원 아이디 : " + id);
        }
        return member;
    }

    public Member find(@NonNull String id) {
        return memberRepository.findByMemberId(id);
    }

}