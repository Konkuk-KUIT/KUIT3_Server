package kuit.server.temp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
//@RestController
public class TempController {
    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // convert value in request to json object (string)
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody = {}", messageBody);

        // convert json object to UserData object
        UserData userData = objectMapper.readValue(messageBody, UserData.class);
        log.info("username = {}, age = {}", userData.getUsername(), userData.getAge());

        response.getWriter().write("ok");
    }

    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messageBody) throws JsonProcessingException {
        UserData userData = objectMapper.readValue(messageBody, UserData.class);
        log.info("username = {}, age = {}", userData.getUsername(), userData.getAge());

        // @ResponseBody 안붙이면 ok 라는 뷰를 가진 ViewResolver 반환
        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody UserData userData) {
        log.info("username = {}, age = {}", userData.getUsername(), userData.getAge());
        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public String requestBodyJsonV4(HttpEntity<UserData> httpEntity) {
        UserData userData = httpEntity.getBody();
        log.info("username = {}, age = {}", userData.getUsername(), userData.getAge());
        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public UserData requestBodyJsonV5(@RequestBody UserData userData) {
        log.info("username = {}, age = {}", userData.getUsername(), userData.getAge());
        return userData;
    }


}
