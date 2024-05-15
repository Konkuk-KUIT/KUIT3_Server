package kuit.server.dao;

import kuit.server.dto.menu.MenuResponse;
import kuit.server.dto.restaurant.MenuUpdateRequest;
import kuit.server.dto.restaurant.RestaurantMenuRequest;
import kuit.server.dto.restaurant.RestaurantMenuResponse;
import kuit.server.dto.restaurant.RestaurantOrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    public List<RestaurantMenuResponse> getMenu(Long restaurantId) {
        String sql = "SELECT * FROM menu m " +
                "JOIN store s ON m.store_id = s.store_id " +
                "WHERE m.store_id = ?";

        return jdbcTemplate.query(sql, new Object[]{restaurantId},(rs, rowNum) -> {
            RestaurantMenuResponse menu = new RestaurantMenuResponse();

            menu.setMenuName(rs.getString("menu_name"));
            menu.setPrice(rs.getInt("price"));

            return menu;
        });

    }

    public void createMenu(Long restaurantId, RestaurantMenuRequest menuRequest) {
        String sql = "INSERT INTO menu (store_id, menu_name, price, status,created_at,updated_at) VALUES (?, ?, ?, ?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, restaurantId);
            ps.setString(2, menuRequest.getMenuName());
            ps.setInt(3, menuRequest.getPrice());
            ps.setString(4, "active");
            ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            ps.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
            return ps;
        }, keyHolder);

    }

    public void modifyMenuStatus_delete(Long restaurantId, Long menuId) {
        String sql = "update menu set status=? where menu_id=? AND store_id=?";
        jdbcTemplate.update(sql, "deleted", menuId, restaurantId);
    }

    public void modifyMenu(Long restaurantId, Long menuId, MenuUpdateRequest menuUpdateRequest) {
        String sql = "update menu set menu_name=?, price=?, updated_at=? where menu_id=? AND store_id=?";
        jdbcTemplate.update(sql, menuUpdateRequest.getMenu_name(),menuUpdateRequest.getPrice(),
                Timestamp.valueOf(LocalDateTime.now()),menuId, restaurantId);
    }

    public void modifyStatus_delete(Long restaurantId) {
        String sql = "update store set status=?, updated_at=? where store_id=?";
        jdbcTemplate.update(sql, "deleted", Timestamp.valueOf(LocalDateTime.now()),restaurantId);
    }
}
