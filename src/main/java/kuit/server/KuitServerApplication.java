package kuit.server;

import kuit.server.controller.PostUserRequestValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class KuitServerApplication{

    public static void main(String[] args) {SpringApplication.run(KuitServerApplication.class, args);}
}
