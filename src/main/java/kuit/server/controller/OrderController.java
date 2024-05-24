package kuit.server.controller;

import kuit.server.common.exception.OrderException;
import kuit.server.common.response.BaseResponse;
import kuit.server.dto.order.GetOrderResponse;
import kuit.server.dto.order.PostOrderRequest;
import kuit.server.dto.order.PostOrderResponse;
import kuit.server.service.OrderService;
import kuit.server.util.BindingResultUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static kuit.server.common.response.status.BaseExceptionResponseStatus.INVALID_ORDER_VALUE;
import static kuit.server.util.BindingResultUtils.getErrorMessages;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    /**
     * 회원 주문 생성
     */
    @PostMapping("")
    public BaseResponse<PostOrderResponse> createOrder(@Validated @RequestBody PostOrderRequest postOrderRequest,
                                                       @RequestParam long userId, BindingResult bindingResult) {
        log.info("[OrderController.createOrder]");
        if (bindingResult.hasErrors()) {
            throw new OrderException(INVALID_ORDER_VALUE, getErrorMessages(bindingResult));
        }
        return new BaseResponse<>(orderService.createOrder(postOrderRequest, userId));
    }

    /**
     * 회원 주문 내역 조회
     */
    @GetMapping("/user/{userId}")
    public BaseResponse<List<GetOrderResponse>> getOrdersByUserId(@PathVariable long userId) {
        log.info("[OrderController.getOrdersByUserId] userId: {}", userId);
        return new BaseResponse<>(orderService.getOrdersByUserId(userId));
    }

}
