package kuit.server.common.exception_handler;

import jakarta.annotation.Priority;
import kuit.server.common.exception.ShopException;
import kuit.server.common.exception.UserException;
import kuit.server.common.response.BaseErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static kuit.server.common.response.status.BaseExceptionResponseStatus.INVALID_SHOP_VALUE;


@Slf4j
@Priority(0)
@RestControllerAdvice
public class ShopExceptionControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ShopException.class)
    public BaseErrorResponse handle_ShopException(ShopException e) {
        log.error("[handle_ShopException]", e);
        return new BaseErrorResponse(INVALID_SHOP_VALUE, e.getMessage());
    }

}
