package kuit.server.dao;

import kuit.server.dto.restaurant.GetRestaurantResponse;
import kuit.server.dto.restaurant.PostRestaurantRequest;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.swing.plaf.basic.BasicTreeUI;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class RestaurantDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public RestaurantDao(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public long createRestaurant(PostRestaurantRequest postRestaurantRequest) {
        String sql = "insert into restaurant(name, location, phone, category, min_order_amount) " +
                "values(:name,:location,:phone,:category,:min_order_amount)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(postRestaurantRequest);
        jdbcTemplate.update(sql, param, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey().longValue());
    }

    public List<GetRestaurantResponse> getRestaurants(String name, String phone) {
        String sql = "select name, location, phone, category, min_order_amount from restaurant " +
                "where name like :name and phone like :phone";

        Map<String, Object> param = Map.of(
                "name", "%" + name + "%",
                "phone", "%" + phone + "%");

        return jdbcTemplate.query(sql, param,
                (rs, rowNum) -> new GetRestaurantResponse(
                        rs.getString("name"),
                        rs.getString("location"),
                        rs.getString("phone"),
                        rs.getString("category"),
                        rs.getInt("min_order_amount"))
        );
    }
}

