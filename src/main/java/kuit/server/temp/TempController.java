package kuit.server.temp;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
// @RestController 붙이면 밑에 @ResponseBody 다 안붙여도 됨
public class TempController {
    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messagebody = {}", messageBody);
        UserData userData = objectMapper.readValue(messageBody, UserData.class);
        objectMapper.readValue(messageBody, UserData.class);
        log.info("username= {}, age = []", userData.getUsername(), userData.getAge());
        response.getWriter().write("OK");
    }

    @ResponseBody
    @PostMapping("/reqeust-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException {
        UserData userData = objectMapper.readValue(messageBody, UserData.class);
        log.info("username= {}, age = []", userData.getUsername(), userData.getAge());
        return "OK";
    }

    @ResponseBody
    @PostMapping("/reqeust-body-json-v3")
    public String requestBodyJsonV3(@RequestBody UserData userData) throws IOException {
        log.info("username= {}, age = []", userData.getUsername(), userData.getAge());
        return "OK";
    }

    @ResponseBody
    @PostMapping("/reqeust-body-json-v4")
    public String requestBodyJsonV4(HttpEntity<UserData> httpEntity) throws IOException {
        UserData data = httpEntity.getBody();
        log.info("username= {}, age = []", data.getUsername(), data.getAge());
        return "OK";
    }

    // 이건 JSON객체를 보내는 방법
    @ResponseBody
    @PostMapping("/reqeust-body-json-v5")
    public UserData requestBodyJsonV5(@RequestBody UserData userData) throws IOException {
        log.info("username= {}, age = []", userData.getUsername(), userData.getAge());
        return userData;
    }

}
