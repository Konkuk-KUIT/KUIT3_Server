package kuit.server.controller;

import kuit.server.common.response.BaseErrorResponse;
import kuit.server.common.response.BaseResponse;
import kuit.server.temp.UserData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static kuit.server.common.response.status.BaseExceptionResponseStatus.BAD_REQUEST;

@RestController
public class ResponseController {
    @RequestMapping("/base-response")
    public BaseResponse<UserData> getSuccessResponse() {
        UserData userData = new UserData("kim", 20);
        return new BaseResponse<>(userData);
    }

    @RequestMapping("/base-error-response")
    public BaseErrorResponse getErrorResponse() {
        return new BaseErrorResponse(BAD_REQUEST);
    }
}
