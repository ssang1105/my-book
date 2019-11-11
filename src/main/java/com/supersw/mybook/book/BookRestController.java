package com.supersw.mybook.book;

import static com.supersw.mybook.config.CookieGeneratorConfig.MY_BOOK_SESSION_ID;

import com.supersw.mybook.book.domain.BookSearchResult;
import com.supersw.mybook.book.service.BookService;
import com.supersw.mybook.error.exceptions.NoAuthorityException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/books")
public class BookRestController {

    private final BookService bookService;

    @GetMapping
    public BookSearchResult get(HttpServletRequest request,
                                @RequestParam String query,
                                @RequestParam(required = false, defaultValue = "1") int page,
                                @RequestParam(required = false, defaultValue = "10") int size) {
        Cookie cookie = WebUtils.getCookie(request, MY_BOOK_SESSION_ID);
        if (cookie == null) {
            throw new NoAuthorityException("인증을 위한 쿠키가 없습니다.");
        }
        return bookService.searchBooks(query, page, size, new String(Base64Utils.decodeFromString(cookie.getValue())));
    }

}
