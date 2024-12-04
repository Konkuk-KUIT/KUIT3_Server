package kuit.server.service;

import kuit.server.dao.OrderDao;
import kuit.server.dto.order.GetOrderResponse;
import kuit.server.dto.order.PostOrderRequest;
import kuit.server.dto.order.PostOrderResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderDao orderDao;

    @Transactional
    public PostOrderResponse createOrder(PostOrderRequest request, long userId) {
        return orderDao.createOrder(request, userId);
    }

    @Transactional(readOnly = true)
    public List<GetOrderResponse> getOrdersByUserId(long userId) {
        return orderDao.getOrdersByUserId(userId);
    }
}
