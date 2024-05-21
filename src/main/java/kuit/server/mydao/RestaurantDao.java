package kuit.server.mydao;

import kuit.server.mydto.retaurant.GetCategoryResponse;
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


    public List<GetCategoryResponse> listUpCategories() {
        String sql = "select distinct category from restaurant;";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            GetCategoryResponse category = new GetCategoryResponse();
            category.setCategory(rs.getString("category"));
            return category;
        });
    }
}
