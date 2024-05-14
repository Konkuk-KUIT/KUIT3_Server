package kuit.server.dao;

import kuit.server.dto.restaurant.GetCategoryResponse;
import kuit.server.dto.restaurant.GetRestaurantResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class RestaurantDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public RestaurantDao(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<GetRestaurantResponse> getRestaurants() {
        String sql = "SELECT * FROM restaurant";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new GetRestaurantResponse(
                rs.getString("name"),
                rs.getString("category"),
                rs.getString("address"),
                rs.getString("phoneNumber"),
                rs.getString("status")
        ));
    }

    public List<GetCategoryResponse> getCategories() {
        String sql = "SELECT type FROM category";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new GetCategoryResponse(
                rs.getString("type")
        ));
    }

    public List<GetRestaurantResponse> getRestaurantsByCategory(Long categoryId) {
        String sql = "SELECT r.* FROM restaurant r INNER JOIN restaurant_category rc ON r.restaurantId = rc.restaurantId WHERE rc.categoryId = :categoryId";
        Map<String, Object> param = new HashMap<>();
        param.put("categoryId", categoryId);
        return jdbcTemplate.query(sql, param, (rs, rowNum) -> new GetRestaurantResponse(
                rs.getString("name"),
                rs.getString("category"),
                rs.getString("address"),
                rs.getString("phoneNumber"),
                rs.getString("status")
        ));
    }
}
