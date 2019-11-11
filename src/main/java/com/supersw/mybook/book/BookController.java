package com.supersw.mybook.book;

import static com.supersw.mybook.config.CookieGeneratorConfig.MY_BOOK_SESSION_ID;

import com.supersw.mybook.member.domain.Member;
import com.supersw.mybook.member.service.MemberService;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.util.WebUtils;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final MemberService memberService;

    @GetMapping("/main")
    public String main(HttpServletRequest request, Model model) {
        Cookie cookie = WebUtils.getCookie(request, MY_BOOK_SESSION_ID);
        model.addAttribute("isAnonymous", true);
        if (cookie == null) {
            return "/main";
        }

        Member member = memberService.find(new String(Base64Utils.decodeFromString(cookie.getValue())));
        if (member != null) {
            model.addAttribute("isAnonymous", false);
            model.addAttribute("memberId", member.getMemberId());
        }

        return "/main";
    }

}
