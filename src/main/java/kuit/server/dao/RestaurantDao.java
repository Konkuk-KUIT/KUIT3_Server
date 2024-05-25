package kuit.server.dao;

import kuit.server.dto.restaurant.GetRestaurantResponse;
import kuit.server.dto.restaurant.PostRestaurantRequest;
import lombok.extern.slf4j.Slf4j;
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

    /*
    신규 식당 추가
     */
    public long create(PostRestaurantRequest postRestaurantRequest){
        String sql = "insert into restaurant(name, address, phone_number, category) " +
                "values(:name, :address, :phone_number, :category);";
        SqlParameterSource param = new BeanPropertySqlParameterSource(postRestaurantRequest);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, param, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public List<GetRestaurantResponse> findRestaurantsByCategory(int categoryId) {
        String sql = "select * " +
                "from restaurant where category = :category;";
        Map<String, Integer> param = Map.of("category", categoryId);

        return jdbcTemplate.query(sql, param,
                (rs, rowNum) -> new GetRestaurantResponse(
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("phone_number"),
                        rs.getString("business_hour"),
                        rs.getString("closed_day"),
                        Integer.parseInt(rs.getString("category")),
                        Integer.parseInt(rs.getString("minimum_order_price")),
                        rs.getString("status"),
                        Integer.parseInt(rs.getString("star_rate")),
                        Integer.parseInt(rs.getString("delivery_fee"))
                )
        );
    }

    public boolean existsWithId(long restaurantId) {
        log.info("exists:: ");
        String sql = "select exists(select name from restaurant where restaurant_id = :restaurant_id)";
        Map<String, Long> param = Map.of("restaurant_id", restaurantId);
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, param, boolean.class));
    }

    public int modifyStatusAsClosed(Long restaurantId) {
        String sql = "update restaurant set status=:status where restaurant_id=:id";
        Map<String, Object> param = Map.of(
                "status", "closed",
                "id", restaurantId);
        return jdbcTemplate.update(sql, param);
    }


    public int modifyBusinessHour(Long restaurantId, String businessHour) {
        String sql = "UPDATE restaurant SET business_hour = :hour where restaurant_id = :id";
        Map<String, Object> param = Map.of(
                "hour", businessHour,
                "id", restaurantId
        );
        return jdbcTemplate.update(sql, param);
    }

    public List<GetRestaurantResponse> search(String keyword, String minStar, String maxDeliveryFee) {
        String sql = "SELECT * " +
                "FROM restaurant WHERE 1=1";

        if(keyword != null && !keyword.isEmpty()){
            sql += " AND name LIKE '%" + keyword + "%'";
        }
        if(minStar != null && !minStar.isEmpty()){
            sql += " AND star_rate >=" + minStar;
        }
        if(maxDeliveryFee != null && !maxDeliveryFee.isEmpty()){
            sql += " AND delivery_fee <= " + maxDeliveryFee;
        }

        log.info("restaurantService.search :: sql = " + sql);

        return jdbcTemplate.query(sql,
                (rs, rowNum) -> new GetRestaurantResponse(
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("phone_number"),
                        rs.getString("business_hour"),
                        rs.getString("closed_day"),
                        Integer.parseInt(rs.getString("category")),
                        Integer.parseInt(rs.getString("minimum_order_price")),
                        rs.getString("status"),
                        Integer.parseInt(rs.getString("star_rate")),
                        Integer.parseInt(rs.getString("delivery_fee"))
                )
        );
    }
}
