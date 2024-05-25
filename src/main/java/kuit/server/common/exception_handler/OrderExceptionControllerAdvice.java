package kuit.server.common.exception_handler;

import static kuit.server.common.response.status.BaseExceptionResponseStatus.ORDER_ERROR;

import jakarta.annotation.Priority;
import kuit.server.common.exception.OrderException;
import kuit.server.common.response.BaseErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@Priority(0)
@RestControllerAdvice
public class OrderExceptionControllerAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(OrderException.class)
    public BaseErrorResponse handle_UserException(OrderException e) {
        log.error("[handle_OrderException]", e);
        return new BaseErrorResponse(ORDER_ERROR, e.getMessage());
    }
}
