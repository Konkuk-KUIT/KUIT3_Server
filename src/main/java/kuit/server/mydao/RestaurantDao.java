package kuit.server.mydao;

import kuit.server.mydto.retaurant.*;
import kuit.server.mydto.retaurant.menu.PostMenuReq;
import kuit.server.mydto.retaurant.menu.RestaurantMenuResp;
import kuit.server.myservice.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.*;

@Slf4j
@Repository
public class RestaurantDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public RestaurantDao(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public static final int DEFAULT_SIZE = 3;

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
        String sql = "select f.name as food_name, f.price as food_price " +
                "from food f join restaurant r on f.restaurant_PK = r.restaurant_PK " +
                "where f.restaurant_PK = :restaurant_PK";
        Map<String, Object> param = Map.of("restaurant_PK", restaurant_PK);
        return jdbcTemplate.query(sql, param, (rs, rowNum) -> {
            RestaurantMenuResp foodInfo = new RestaurantMenuResp(
                    rs.getString("food_name"),
                    rs.getLong("food_price"));
            return foodInfo;
        });
    }

    public List<GetCategoryResp> listUpCategories() {
        String sql = "select distinct category from restaurant;";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            GetCategoryResp category = new GetCategoryResp();
            category.setCategory(rs.getString("category"));
            return category;
        });
    }

    public List<CategorizedRestaurantEntity> getCategorizedRestaurantRespList(String category, PageCondition pageCondition) {
        String sql = "select * from restaurant where category = :categoryy and " +
                "restaurant_PK >= :lastId and price >= :min_price " +
                "limit :limit";
        Map<String, Object> param = Map.of(
                "category", category,
                "lastId", pageCondition.getLastId(),
                "min_price", pageCondition.getNumSortBy(),
                "limit", DEFAULT_SIZE
        );
        return jdbcTemplate.query(sql, param, (rs, rowNum) -> {
            CategorizedRestaurantEntity restaurant = new CategorizedRestaurantEntity();
            restaurant.setName(rs.getString("name"));
            restaurant.setMin_price(rs.getLong("min_price"));
            restaurant.setPic_URL(rs.getString("pic_URL"));
            return restaurant;
        });
    }

    public Long addNewFood(long restaurant_PK, PostMenuReq postMenuReq) {
        log.info("RestaurantDao.addNewFood");
        String sql = "insert into food (restaurant_PK, name, price, explanation, pic_URL)" +
                " values(:restaurant_PK, :name, :price, :explanation, :pic_URL);";
        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("restaurant_PK", restaurant_PK)
                .addValue("name", postMenuReq.getName())
                .addValue("price", postMenuReq.getPrice())
                .addValue("explanation", postMenuReq.getExplanation())
                .addValue("pic_URL", postMenuReq.getPic_URL());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, param, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public boolean isExist(long restaurantPk) {
        log.info("RestaurantDao.checkRestaurantExists");
        String sql = "select exists(select restaurant_PK from restaurant where restaurant_PK = :restaurant_PK);";
        Map<String, Object> param = Map.of("restaurant_PK", restaurantPk);
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, param, boolean.class));
    }


}
