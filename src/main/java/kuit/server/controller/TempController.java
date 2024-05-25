package kuit.server.controller;


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
public class TempController {
    private ObjectMapper objectMapper = new ObjectMapper();
    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException{
        ServletInputStream inputStream  = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody = {}",messageBody);
        UserData userdata = objectMapper.readValue(messageBody, UserData.class);
        log.info("username ={},age={}",userdata.getUsername(),userdata.getAge());
        response.getWriter().write("ok");
    }
    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException{
        UserData userdata = objectMapper.readValue(messageBody, UserData.class);
        log.info("username ={},age={}",userdata.getUsername(),userdata.getAge());
        return  "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody UserData userdata) throws IOException{
        log.info("username ={},age={}",userdata.getUsername(),userdata.getAge());
        return  "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public String requestBodyJsonV4(HttpEntity<UserData> httpEntity) throws IOException{
        UserData userdata = httpEntity.getBody();
        log.info("username ={},age={}",userdata.getUsername(),userdata.getAge());
        return  "ok";
    }
    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public UserData requestBodyJsonV5(@RequestBody UserData userdata) throws IOException{
        log.info("username ={},age={}",userdata.getUsername(),userdata.getAge());
        return userdata;
    }
}
