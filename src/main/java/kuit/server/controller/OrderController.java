package kuit.server.controller;

import kuit.server.dto.order.PostOrderRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OrderController {
    @PostMapping("/test")
    @ResponseBody
    public String test(@RequestBody PostOrderRequest postOrderRequest) {
        System.out.println(postOrderRequest);
        return "Success?";
    }
}
