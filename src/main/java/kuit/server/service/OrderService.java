package kuit.server.service;

import static kuit.server.common.response.status.BaseExceptionResponseStatus.DATABASE_ERROR;
import static kuit.server.common.response.status.BaseExceptionResponseStatus.WRONG_ORDER_ID;

import kuit.server.common.exception.DatabaseException;
import kuit.server.common.exception.OrderException;
import kuit.server.dao.OrderDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderDao orderDao;
    public void cancelOrder(long orderId) {
        int effectedRow = orderDao.cancelOrder(orderId);
        if(effectedRow >= 1) {
            throw new DatabaseException(DATABASE_ERROR);
        }
        if(effectedRow == 0) {
            throw new OrderException(WRONG_ORDER_ID);
        }
    }
}
