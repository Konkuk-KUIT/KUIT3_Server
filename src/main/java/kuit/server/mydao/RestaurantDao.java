package kuit.server.mydao;

import kuit.server.mydto.retaurant.GetCategorizedRestaurantResp;
import kuit.server.mydto.retaurant.GetCategoryResp;
import kuit.server.mydto.retaurant.RestaurantReq;
import kuit.server.mydto.retaurant.menu.RestaurantMenuResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Slf4j
@Repository
public class RestaurantDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public RestaurantDao(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public long enrollRestaurant(RestaurantReq restaurantReq) {
        log.info("RestaurantDao.enrollRestaurant");
        String sql = "insert into restaurant(name, min_price, category, pic_URL) " +
                "values(:name, :min_price, :category, :pic_URL)";
        SqlParameterSource param = new BeanPropertySqlParameterSource(restaurantReq);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, param, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }


    public List<RestaurantMenuResp> getRestaurantFoods(long restaurant_PK) {
        log.info("RestaurantDao.getRestaurantFoods");
        String sql = "select * from food f join restaurant r on f.restaurant_PK = r.restaurant_PK" +
                "where f.restaurant_PK = ?";
        Map<String, Object> param = Map.of("restaurant_PK", restaurant_PK);
        return jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<>(RestaurantMenuResp.class));
    }


    public List<GetCategoryResp> listUpCategories() {
        String sql = "select distinct category from restaurant;";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            GetCategoryResp category = new GetCategoryResp();
            category.setCategory(rs.getString("category"));
            return category;
        });
    }

    public List<GetCategorizedRestaurantResp> getCategorizedRestaurants(String category, long min_price) {
        String sql = "select * from restaurant where category = :category and min_price >= :min_price";

        Map<String, Object> param = new HashMap<>();
        param.put("category", category);
        param.put("min_price", min_price);

        return jdbcTemplate.query(sql,param,(rs, rowNum) -> {
            GetCategorizedRestaurantResp restaurantResp = new GetCategorizedRestaurantResp();
            restaurantResp.setName(rs.getString("name"));
            restaurantResp.setMin_price(rs.getLong("min_price"));
            restaurantResp.setCategory(rs.getString("category"));
            restaurantResp.setPic_URL(rs.getString("pic_URL"));
            return restaurantResp;
        });
    }
}
