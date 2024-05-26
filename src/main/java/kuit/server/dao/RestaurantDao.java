package kuit.server.dao;

import kuit.server.dto.restaurant.GetRestaurantResponse;
import kuit.server.dto.restaurant.PostRestaurantRequest;

import kuit.server.dto.user.PostUserRequest;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.swing.plaf.basic.BasicTreeUI;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class RestaurantDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public RestaurantDao(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public long createRestaurant(PostRestaurantRequest postRestaurantRequest) {
        String sql = "insert into restaurant(name, location, phone, category, min_order_amount) " +
                "values(:name,:location,:phone,:category,:minOrderAmount)";

        SqlParameterSource param = new BeanPropertySqlParameterSource(postRestaurantRequest);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, param, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey().longValue());
    }

    public List<GetRestaurantResponse> getRestaurants(String name, String phone, String sortBy, String orderBy) {
        String sql = "select name, location, phone, category, min_order_amount from restaurant " +
                "where name like :name and phone like :phone ";

        // 허용된 정렬 기준 및 순서 목록
        List<String> validSortBy = List.of("min_order_amount");
        List<String> validOrderBy = List.of("asc", "desc");

        if (validSortBy.contains(sortBy) && validOrderBy.contains(orderBy)) {
            sql += "order by " + sortBy + " " + orderBy;
        }

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

    public Set<String> getCategories() {
        String sql = "select distinct category from restaurant";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("category"))
                .stream()
                .collect(Collectors.toSet());
    }

}

