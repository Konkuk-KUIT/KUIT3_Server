package kuit.server.controller.validator;

import kuit.server.dto.member.request.PostMemberRequest;
import kuit.server.dto.user.PostUserRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;

@Component
public class PostMemberRequestValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return PostUserRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PostMemberRequest postUserRequest = (PostMemberRequest) target;
        if (postUserRequest.getEmail()==null){
            //예외처리
            FieldError fieldError = new FieldError("postUserRequest", "email", "이메일 형식이 아닙니다");
            errors.rejectValue("postUserRequest","email","이메일 형식이 아닙니다");

        }
        if(postUserRequest.getEmail()==null){

        }
        if(postUserRequest.getPassword()==null){

        }
    }
}
