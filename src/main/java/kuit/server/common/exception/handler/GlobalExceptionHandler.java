package kuit.server.common.exception.handler;

import kuit.server.common.exception.RestaurantException;
import kuit.server.common.response.BaseErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(RestaurantException.class)
    public ResponseEntity<BaseErrorResponse> handleRestaurantException(RestaurantException ex) {
        BaseErrorResponse errorResponse = new BaseErrorResponse(ex.getExceptionStatus(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ex.getExceptionStatus().getStatus()));
    }

}