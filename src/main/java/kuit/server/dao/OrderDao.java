package kuit.server.dao;

import java.util.Map;
import kuit.server.dto.order.PostOrderRequest;
import kuit.server.dto.user.GetUserResponse;
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

    public GetUserResponse getUserByUserId(long orderId) {
        String sql = "select email, phone_number, nickname, status from user " +
                "where order_id=:order_id";
        Map<String, Object> param = Map.of("order_id", orderId);

        return jdbcTemplate.queryForObject(sql, param, new BeanPropertyRowMapper<>(GetUserResponse.class));

    }

    public void registerOrder(PostOrderRequest postOrderRequest) {  // TODO: 하하하
        String sql = "insert all";
    }

    public int cancelOrder(long orderId) {
        String sql = "update `order` set status=:status where order_id=:order_id";
        Map<String, Object> param = Map.of(
                "status", "deleted",
                "order_id", orderId);
        return jdbcTemplate.update(sql, param); // returns affected row
    }
}
