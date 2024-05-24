package kuit.server.dao;

import kuit.server.dto.menu.GetMenuResponse;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository
public class MenuDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public MenuDao(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<GetMenuResponse> getMenus(long restaurantId) {
        String sql = "select menu_name, price, menu_detail, menu_status from menu " +
                "where store_id=:restaurantId";

        Map<String, Object> param = Map.of(
                "restaurantId", restaurantId);

        return jdbcTemplate.query(sql, param,
                (rs, rowNum) -> new GetMenuResponse(
                        rs.getString("menu_name"),
                        rs.getInt("price"),
                        rs.getString("menu_detail"),
                        rs.getString("menu_status"))
        );
    }
}
