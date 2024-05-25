package kuit.server.common.exception_handler;

import kuit.server.common.response.BaseErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static kuit.server.common.response.status.BaseExceptionResponseStatus.BAD_REQUEST;

@Slf4j
@RestControllerAdvice
public class TempExceptionControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public BaseErrorResponse runtimeExeptionHandler(RuntimeException e){
        log.error("RuntimeExecprionHandler = ",e);

        return new BaseErrorResponse(BAD_REQUEST,e.getMessage());
    }
}
