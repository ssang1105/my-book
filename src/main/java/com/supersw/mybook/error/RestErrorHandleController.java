package com.supersw.mybook.error;

import static com.supersw.mybook.error.ErrorResult.DEFAULT;
import static com.supersw.mybook.error.RestErrorHandleController.ORDER;

import com.supersw.mybook.error.exceptions.AlreadyExistMemberException;
import com.supersw.mybook.error.exceptions.NoAuthorityException;
import com.supersw.mybook.error.exceptions.NotExistMemberException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RequiredArgsConstructor
@Order(ORDER)
@RestControllerAdvice(annotations = RestController.class)
public class RestErrorHandleController {

    public static final int ORDER = 0;

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResult handleError(Exception e) {
        log.error(e.getMessage(), e);
        return DEFAULT;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AlreadyExistMemberException.class)
    public ErrorResult handleAlreadyExistMemberException(AlreadyExistMemberException e) {
        log.warn(e.getMessage(), e);
        return ErrorResult.of("이미 가입한 회원입니다.", e);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotExistMemberException.class)
    public ErrorResult handleNotExistMemberException(NotExistMemberException e) {
        log.warn(e.getMessage(), e);
        return ErrorResult.of("존재하지 않는 회원입니다.", e);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(NoAuthorityException.class)
    public ErrorResult handleNoAuthorityException(NoAuthorityException e) {
        log.warn(e.getMessage(), e);
        return ErrorResult.of("인증되지 않은 회원입니다.", e);
    }


}
