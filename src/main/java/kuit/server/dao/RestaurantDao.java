package kuit.server.dao;

import kuit.server.dto.menu.MenuResponse;
import kuit.server.dto.restaurant.RestaurantOrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;

@Repository
public class RestaurantDao {
    private final JdbcTemplate jdbcTemplate;
    private final MenuDao menuDao;

    public RestaurantDao(DataSource dataSource, MenuDao menuDao) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.menuDao = menuDao;
    }


    public List<RestaurantOrderResponse> getOrders(Long restaurantId) {
        String sql = "SELECT * FROM store s JOIN orders o ON s.store_id = o.store_id WHERE s.store_id = ?";
        return jdbcTemplate.query(sql, new Object[]{restaurantId}, (rs, rowNum) -> {
            RestaurantOrderResponse order = new RestaurantOrderResponse();

            order.setOrderId(rs.getLong("order_id"));
            order.setUserId(rs.getLong("user_id"));

            List<MenuResponse> menuList = menuDao.getMenusByOrderId(rs.getLong("order_id"));
            order.setMenuList(menuList != null ? menuList : Collections.emptyList()); // 메뉴 정보가 없으면 null로 설정

            return order;
        });
    }
}
