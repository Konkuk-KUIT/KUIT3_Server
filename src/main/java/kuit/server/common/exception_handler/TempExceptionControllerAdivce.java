package kuit.server.common.exception_handler;

import jakarta.annotation.Priority;
import kuit.server.common.exception.UserException;
import kuit.server.common.response.BaseErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static kuit.server.common.response.status.BaseExceptionResponseStatus.INVALID_USER_VALUE;
@Priority(0) // 우선순위 설정 아니면 BaseException이 먼저 실행된다.
@Slf4j
@RestControllerAdvice
// 모든 Controller에 적용
public class TempExceptionControllerAdivce {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserException.class)
    public BaseErrorResponse handle_UserException(UserException e) {
        log.error("[handle_UserException]", e);
        return new BaseErrorResponse(INVALID_USER_VALUE, e.getMessage());
    }

}
