package kuit.server.dao;

import kuit.server.dto.restaurant.GetRestaurant;
import kuit.server.dto.restaurant.GetcategoryResponse;
import kuit.server.dto.restaurant.PostRestaurantRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Repository
public class RestaurantDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public RestaurantDao(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<GetcategoryResponse> getCategory(String category) {
        String sql = "select * from restaurants where category=:category";

        Map<String, Object> param = Map.of(
                "category", "%" + category + "%");

        return jdbcTemplate.query(sql, param,
                (rs, rowNum) -> new GetcategoryResponse(
                        rs.getString("category"),
                        rs.getString("storeName"))
        );
    }
    public List<GetRestaurant> getRestaurants() {
        String sql = "select * from restaurants";

        return jdbcTemplate.query(sql,
                (rs, rowNum) -> new GetRestaurant(
                        rs.getString("restaurantname"),
                        rs.getString("category"),
                        rs.getLong("min_order_amount"))
        );
    }
    public long createRestaurant(PostRestaurantRequest postRestaurantRequest){
        String sql = "insert into restaurants(restaurantname,category,min_order_amount,status) values(:restaurantname,:category,:min_order_amount,:status)";

        SqlParameterSource param = new BeanPropertySqlParameterSource(postRestaurantRequest);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql,param,keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }
}
