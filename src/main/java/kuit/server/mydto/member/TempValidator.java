package kuit.server.mydto.member;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;

public class TempValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return PostMemberReq.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PostMemberReq postMemberReq = (PostMemberReq) target;
        if(postMemberReq.getEmail() == null) {
            FieldError fieldError = new FieldError("postMemberReq", "email", "이메일을 작성해주세요");
        }
        if(postMemberReq.getPhoneNumber() == null) {
            FieldError fieldError = new FieldError("postMemberReq", "phoneNumber", "번호를 입력해주세요");
        }
    }
}

