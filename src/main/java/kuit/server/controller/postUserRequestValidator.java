package kuit.server.controller;

import jakarta.validation.ConstraintViolation;

import jakarta.validation.executable.ExecutableValidator;
import jakarta.validation.metadata.BeanDescriptor;
import kuit.server.dto.store.PostStoreRequest;
import kuit.server.dto.user.PostUserRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import java.util.Set;

@Component
public class postUserRequestValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return PostStoreRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PostUserRequest postUserRequest = (PostUserRequest) target;
    }
}
