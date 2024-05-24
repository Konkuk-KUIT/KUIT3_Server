package kuit.server.controller;

import kuit.server.dto.order.PostOrderRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @PostMapping("/test")
    public String test(@RequestBody PostOrderRequest postOrderRequest) {
        System.out.println(postOrderRequest);
        return "Success?";
    }

//    public String registerOrder(@RequestBody PostOrderRequest postOrderRequest) {
//
//    }
}
