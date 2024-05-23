package kuit.server.temp;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Controller
@Slf4j
public class TempController {

    private ObjectMapper objectMapper=new ObjectMapper();

    @GetMapping(value = "/v1")
    @ResponseBody
    public String getViewV1(){

        return "<h1>getView</h1>";
    }

    @GetMapping(value = "/v2")
    @ResponseBody
    public UserData getViewV2(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        ServletInputStream inputStream = httpServletRequest.getInputStream();
        String messageBody= StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        UserData userData = objectMapper.readValue(messageBody, UserData.class);
        log.info("userData: ",userData.toString());

        return userData;
    }

    @GetMapping(value = "/v3")
    @ResponseBody
    public String getViewV3(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        ServletInputStream inputStream = httpServletRequest.getInputStream();
        String messageBody= StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        UserData userData = objectMapper.readValue(messageBody, UserData.class);
        log.info("userData: ",userData.toString());

        return userData.toString();
    }
}
