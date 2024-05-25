package kuit.server.dao;

import kuit.server.dto.restaurant.GetCategoryResponse;
import kuit.server.dto.restaurant.GetStoreResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class RestaurantDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public RestaurantDao(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<GetStoreResponse> getAllStores() {
        String sql = "select * from Store";

        return jdbcTemplate.query(sql,
                (rs, rowNum) -> new GetStoreResponse(
                        rs.getString("store_name"),
                        rs.getLong("delivery_time"),
                        rs.getString("phone_number"),
                        rs.getString("operating_time"),
                        rs.getString("store_status")
                ));
    }
    public List<GetStoreResponse> getStoresSortedByMinimumPrice(long minimumPrice) {
        String sql = "select * from Store where minimum_price >= :minimumPrice order by minimum_price";

        Map<String, Long> param = Map.of(
                "minimumPrice", minimumPrice);

        return jdbcTemplate.query(sql, param,
                (rs, rowNum) -> new GetStoreResponse(
                        rs.getString("store_name"),
                        rs.getLong("delivery_time"),
                        rs.getString("phone_number"),
                        rs.getString("operating_time"),
                        rs.getString("store_status")
                ));
    }
    public List<GetCategoryResponse> getCategories() {
        String sql = "select * from category";

        return jdbcTemplate.query(sql,
                (rs, rowNum) -> new GetCategoryResponse(
                        rs.getString("content")
        ));

    }

    public List<GetStoreResponse> getCategoryStores(long categoryId) {
        String sql = "select store_name, delivery_time, phone_number, operating_time, store_status from Store "
        + "where store_status='open' and id3=:categoryId";

        Map<String, Long> param = Map.of(
                "categoryId", categoryId);

        return jdbcTemplate.query(sql, param, (rs, rowNum) -> new GetStoreResponse(
                        rs.getString("store_name"),
                        rs.getLong("delivery_time"),
                        rs.getString("phone_number"),
                        rs.getString("operating_time"),
                        rs.getString("store_status")
        ));

    }

}
