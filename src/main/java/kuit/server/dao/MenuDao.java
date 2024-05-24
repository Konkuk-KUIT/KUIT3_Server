package kuit.server.dao;

import kuit.server.dto.menu.GetMenuResponse;
import kuit.server.dto.menu.PostMenuRequest;
import kuit.server.dto.restaurant.PostRestaurantMenuRequest;
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
public class MenuDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public MenuDao(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
    public long createMenu(PostMenuRequest postMenuRequest) {
        String sql = "insert into menu(name, image_url, description, price, restaurant_id) " +
                "values(:name, :image_url, :description, :price, :restaurant_id)";
        SqlParameterSource param = new BeanPropertySqlParameterSource(postMenuRequest); // 네임드 파라미터를 자동으로 매핑해줌
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, param, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public List<GetMenuResponse> getMenus(long restaurantId) {
        log.info("MenuDao.getMenus");
        String sql = "select name, image_url, description, price, status, restaurant_id from menu where restaurant_id = :restaurant_id";
        Map<String, Long> param = Map.of("restaurant_id", restaurantId);
        return jdbcTemplate.query(sql, param,
                (rs, rowNum) -> new GetMenuResponse(
                        rs.getString("name"),
                        rs.getString("image_url"),
                        rs.getString("description"),
                        Integer.parseInt(rs.getString("price")),
                        Long.parseLong(rs.getString("restaurant_id"))
                )
        );
    }

    public List<GetMenuResponse> getMenusByKeyword(String keyword) {
        log.info("menuDao.getByKeyword :: " + keyword);

        String sql = "select name, image_url, description, price, status, restaurant_id from menu " +
                "where name like :name;";

        Map<String, String> param = Map.of("name", "%" + keyword + "%");
        return jdbcTemplate.query(sql, param,
                (rs, rowNum) -> new GetMenuResponse(
                        rs.getString("name"),
                        rs.getString("image_url"),
                        rs.getString("description"),
                        Integer.parseInt(rs.getString("price")),
                        Long.parseLong(rs.getString("restaurant_id"))
                )
        );
    }

    public int modifyPrice(Long menuId, Integer price) {
        String sql = "UPDATE menu SET price = :price WHERE menu_id = :menu_id";

        Map<String, Object> param = Map.of(
            "menu_id", menuId,
            "price", price
        );

        return jdbcTemplate.update(sql, param);
    }
}
