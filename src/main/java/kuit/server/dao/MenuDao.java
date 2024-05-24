package kuit.server.dao;

import kuit.server.dto.menu.GetMenuResponse;
import kuit.server.dto.menu.PostMenuRequest;
import kuit.server.dto.user.PostUserRequest;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
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
    public long addMenu(PostMenuRequest postMenuRequest, long restaurantId) {
        String sql = "INSERT INTO Menu(`menu_name`, `price`, `menu_image`, `menu_detail`, `menu_status`, `created_date`, `modified_date`, `store_id`) " +
                "VALUES(:menu_name, :price, :menu_image, :menu_detail, :menu_status, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, :restaurantId)";

//        SqlParameterSource param = new BeanPropertySqlParameterSource(postMenuRequest);
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        jdbcTemplate.update(sql, param, keyHolder);

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("menu_name", postMenuRequest.getMenu_name());
        paramMap.put("price", postMenuRequest.getPrice());
        paramMap.put("menu_image", postMenuRequest.getMenu_image());
        paramMap.put("menu_detail", postMenuRequest.getMenu_detail());
        paramMap.put("menu_status", postMenuRequest.getMenu_status());
        paramMap.put("restaurantId", restaurantId);

        SqlParameterSource param = new MapSqlParameterSource(paramMap);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, param, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }
}
