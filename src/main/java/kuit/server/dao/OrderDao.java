package kuit.server.dao;

import kuit.server.dto.order.GetOrderResponse;
import kuit.server.dto.order.PostOrderRequest;
import kuit.server.dto.order.PostOrderResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Repository
public class OrderDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public OrderDao(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<GetOrderResponse> getOrdersByUserId(long userId) {
        String sql = "select orderId, paymentMethod, totalPrice, status, userId, restaurantId from `order` " +
                "where userId=:userId";

        Map<String, Object> param = Map.of("userId", userId);

        return jdbcTemplate.query(sql, param,
                (rs, rowNum) -> new GetOrderResponse(
                        rs.getLong("orderId"),
                        rs.getString("paymentMethod"),
                        rs.getInt("totalPrice"),
                        rs.getString("status"),
                        rs.getLong("userId"),
                        rs.getLong("restaurantId"))
        );
    }

    public PostOrderResponse createOrder(PostOrderRequest postOrderRequest, long userId) {
        String sql = "insert into `order`(paymentMethod, totalPrice, status, userId, restaurantId) " +
                "values(:paymentMethod, :totalPrice, 'pending', :userId, :restaurantId)";

        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("paymentMethod", postOrderRequest.getPaymentMethod())
                .addValue("totalPrice", postOrderRequest.getTotalPrice())
                .addValue("userId", userId)
                .addValue("restaurantId", postOrderRequest.getRestaurantId());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, param, keyHolder);

        long orderId = Objects.requireNonNull(keyHolder.getKey()).longValue();

        return new PostOrderResponse(orderId, postOrderRequest.getPaymentMethod(), postOrderRequest.getTotalPrice(), "pending", userId, postOrderRequest.getRestaurantId());
    }
}