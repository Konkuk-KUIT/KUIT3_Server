package kuit.server.controller;

import kuit.server.common.response.BaseResponse;
import kuit.server.dto.order.PostOrderRequest;
import kuit.server.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    @PostMapping("/test")
    public String test(@RequestBody PostOrderRequest postOrderRequest) {
        log.info(postOrderRequest.toString());
        return "Success?";
    }

//    public String registerOrder(@RequestBody PostOrderRequest postOrderRequest) {
//
//    }

    @PatchMapping("/{orderId}/cancel")
    public BaseResponse<String> cancelOrder(@PathVariable long orderId) {
        orderService.cancelOrder(orderId);

        return new BaseResponse<>("orderId=" + orderId + " 삭제처리 되었습니다.");
    }
}
