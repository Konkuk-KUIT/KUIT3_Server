package kuit.server.controller;

import kuit.server.dto.user.PostUserRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;

@Component
public class PostUserRequestValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return PostUserRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PostUserRequest postUserRequest = (PostUserRequest) target;
        // 예외처리
        if(postUserRequest.getEmail() : 형식이 알맞지 않는 경우){
            FieldError fieldError = new FieldError("postUserRequest","email", "이메일 형식이 아닙니다.");
            errors.rejectValue("postUserRequest","email", "이메일 형식이 아닙니다.");
        }
        // 예외처리
        if(postUserRequest.getEmail() == null){

        }
        // 예외처리
        if(postUserRequest.getPassword() == null){
            FieldError fieldError = new FieldError("posrUserRequest","password","비밀번호가 존재하지 않습니다.");
            bindingResult.addError(fieldError);
        }
    }
}
