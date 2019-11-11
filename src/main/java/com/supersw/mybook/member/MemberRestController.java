package com.supersw.mybook.member;

import com.supersw.mybook.member.domain.Member;
import com.supersw.mybook.member.domain.MemberJoinRequest;
import com.supersw.mybook.member.service.MemberService;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.CookieGenerator;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class MemberRestController {

    private final MemberService memberService;
    private final CookieGenerator cookieGenerator;

    @PostMapping("/members")
    public Member join(@RequestBody MemberJoinRequest request) {
        return memberService.join(request.getId(), request.getPassword());
    }

    @PostMapping("/members/login")
    public Member login(@RequestBody MemberJoinRequest request, HttpServletResponse response) {
        Member member = memberService.login(request.getId(), request.getPassword());
        cookieGenerator.addCookie(response, Base64Utils.encodeToString(member.getMemberId().getBytes()));
        return member;
    }

}
