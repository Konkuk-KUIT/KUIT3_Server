package kuit.server.common.exception_handler;

import jakarta.annotation.Priority;
import kuit.server.common.exception.RestaurantException;
import kuit.server.common.exception.UserException;
import kuit.server.common.response.BaseErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static kuit.server.common.response.status.BaseExceptionResponseStatus.INVALID_RESTAURANT_VALUE;

@Slf4j
@Priority(0)
@RestControllerAdvice
public class RestaurantExceptionContorollerAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RestaurantException.class)
    public BaseErrorResponse handle_RestaurantException(RestaurantException e) {
        log.error("[handle_RestaurantException]", e);
        return new BaseErrorResponse(e.getExceptionStatus(), e.getMessage());
    }
}
