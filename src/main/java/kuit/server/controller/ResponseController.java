package kuit.server.controller;

import kuit.server.common.response.BaseErrorResponse;
import kuit.server.common.response.BaseResponse;
import kuit.server.common.response.status.BaseExceptionResponseStatus;
import kuit.server.temp.UserData;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResponseController {

    @RequestMapping("/base-response")
    public BaseResponse<UserData> getSuccessResponse() {
        UserData userData=new UserData("kim");
        return new BaseResponse<>(userData);

    }

    @RequestMapping("/base-error-response")
    public BaseErrorResponse getErrorResponse(){
        return new BaseErrorResponse(BaseExceptionResponseStatus.BAD_REQUEST);
    }
}
