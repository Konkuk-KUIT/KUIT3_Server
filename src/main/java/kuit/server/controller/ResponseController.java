package kuit.server.controller;

import kuit.server.common.response.BaseErrorResponse;
import kuit.server.common.response.BaseResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static kuit.server.common.response.status.BaseExceptionResponseStatus.BAD_REQUEST;

@RestController
public class ResponseController {

    @RequestMapping("/base-response")
    public BaseResponse<UserData> getSuccessResponse(){
        UserData userdata = new UserData("park",20);
        return new BaseResponse<>(userdata);
    }
    @RequestMapping("/base-error-response")
    public BaseErrorResponse getFailedResponse(){
        return new BaseErrorResponse(BAD_REQUEST);
    }
}
