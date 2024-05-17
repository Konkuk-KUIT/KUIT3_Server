package kuit.server.temp;

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
@RestController // @ResponseBody를 적용시켜준다 , @RequestBody는 생략 불가능
public class TempController {
    //Json 객체를 Java 객체로 변환하기 위한 objectMapper
    private ObjectMapper objectMapper = new ObjectMapper();

    // /request-body-json-v1을 가진 POST 요청을 가질 , 아래의 메소드가 실행
    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody={}", messageBody);
        UserData userData= objectMapper.readValue(messageBody,UserData.class);
        log.info("user name:{}, user age:{}", userData.getUsername(),userData.getAge());
        response.getWriter().write("ok");
    }

    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String message) throws IOException{
        UserData userData = objectMapper.readValue(message, UserData.class);
        log.info("user name:{}, user age:{}", userData.getUsername(),userData.getAge());
        return "ok";
    }

    //objectMapper 없이 UserData 객체로 자동 변환 해줌
    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody UserData userData){
        log.info("user name:{}, user age:{}", userData.getUsername(),userData.getAge());
        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public String requestBodyJsonV4(HttpEntity<UserData> httpEntity){
        UserData userData = httpEntity.getBody();
        log.info("user name:{}, user age:{}", userData.getUsername(),userData.getAge());
        return "ok";
    }

    //가장 많이 씀
    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public UserData requestBodyJsonV5(@RequestBody UserData userData){
        log.info("user name:{}, user age:{}", userData.getUsername(),userData.getAge());
        return userData;
    }

}
