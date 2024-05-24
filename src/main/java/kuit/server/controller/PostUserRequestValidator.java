package kuit.server.controller;

import kuit.server.dto.user.PostUserRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
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
        
        // 로직 추가
    }
}
