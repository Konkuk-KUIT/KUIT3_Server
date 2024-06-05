package kuit.server.dao;

import java.util.Map;
import kuit.server.dto.order.GetOrderResponse;
import kuit.server.dto.order.PostOrderRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class OrderDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public void registerOrder(PostOrderRequest postOrderRequest) {  // TODO: 동적 쿼리;;;
        String sql = "insert all";
    }

    public int cancelOrder(long orderId) {
        String sql = "update `order` set status=:status where order_id=:order_id";
        Map<String, Object> param = Map.of(
                "status", "deleted",
                "order_id", orderId);
        return jdbcTemplate.update(sql, param); // returns affected row
    }

    public GetOrderResponse getOrder(long orderId) {
        String sql = "select order_id, status, total from `order` "
                + "where order_id=:order_id";

        Map<String, Object> param = Map.of("order_id", orderId);

        return jdbcTemplate.queryForObject(sql, param, new BeanPropertyRowMapper<>(GetOrderResponse.class));
    }
}
