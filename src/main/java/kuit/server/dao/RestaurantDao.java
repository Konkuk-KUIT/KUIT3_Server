package kuit.server.dao;

import kuit.server.dto.restaurant.PostRestaurantRequest;
import kuit.server.dto.restaurant.RestaurantDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
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

    public boolean hasDuplicateName(String name) {
        String sql = "select exists(select name from Restaurant where name=:name)";
        Map<String, Object> param = Map.of("name", name);
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, param, Boolean.class));
    }

    public long createRestaurant(PostRestaurantRequest postRestaurantRequest) {
        String sql = "insert into Restaurant(name, location, phone, category, min_order_amount, status) " +
                "values(:name, :location, :phone, :category, :minOrderAmount, :status)";

        SqlParameterSource param = new BeanPropertySqlParameterSource(postRestaurantRequest);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, param, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public int modifyRestaurantStatus(long restaurantid, String status) {
        String sql = "update Restaurant set status=:status where restaurantid=:restaurantid";
        Map<String, Object> param = Map.of(
                "status", status,
                "restaurantid", restaurantid);
        return jdbcTemplate.update(sql, param);
    }

    public int modifyRestaurantDetails(long restaurantid, String name, String location, String phone, String category, int minOrderAmount) {
        String sql = "update Restaurant set name=:name, location=:location, phone=:phone, category=:category, min_order_amount=:minOrderAmount where restaurantid=:restaurantid";
        Map<String, Object> param = Map.of(
                "name", name,
                "location", location,
                "phone", phone,
                "category", category,
                "minOrderAmount", minOrderAmount,
                "restaurantid", restaurantid);
        return jdbcTemplate.update(sql, param);
    }
    public List<RestaurantDto> getRestaurants(String name, String location, String status) {
        String sql = "select restaurantid, name, location, phone, category, min_order_amount, status from Restaurant " +
                "where name like :name and location like :location";

        Map<String, Object> param = Map.of(
                "name", "%" + name + "%",
                "location", "%" + location + "%"
        );

        // 상태 필터링이 필요할 경우 추가
        if (status != null && !status.isEmpty()) {
            sql += " and status = :status";
            param = new HashMap<>(param);
            param.put("status", status);
        }

        return jdbcTemplate.query(sql, param,
                (rs, rowNum) -> new RestaurantDto(
                        rs.getLong("restaurantid"),
                        rs.getString("name"),
                        rs.getString("location"),
                        rs.getString("phone"),
                        rs.getString("category"),
                        rs.getInt("min_order_amount"),
                        rs.getString("status"))
        );
    }


    public RestaurantDto getRestaurantById(long restaurantid) {
        String sql = "select restaurantid, name, location, phone, category, min_order_amount, status from Restaurant where restaurantid=:restaurantid";
        Map<String, Object> param = Map.of("restaurantid", restaurantid);
        return jdbcTemplate.queryForObject(sql, param,
                (rs, rowNum) -> new RestaurantDto(
                        rs.getLong("restaurantid"),
                        rs.getString("name"),
                        rs.getString("location"),
                        rs.getString("phone"),
                        rs.getString("category"),
                        rs.getInt("min_order_amount"),
                        rs.getString("status"))
        );
    }

    public long getRestaurantIdByName(String name) {
        String sql = "select restaurantid from Restaurant where name=:name and status='Open'";
        Map<String, Object> param = Map.of("name", name);
        return jdbcTemplate.queryForObject(sql, param, Long.class);
    }
}
