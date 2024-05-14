package kuit.server.dao;

import kuit.server.dto.restaurant.GetRestaurantResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

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
}
