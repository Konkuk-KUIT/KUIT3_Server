package kuit.server.dao;

import kuit.server.dto.menu.GetMenuResponse;
import kuit.server.dto.menu.PostMenuRequest;
import kuit.server.dto.user.GetUserResponse;
import kuit.server.dto.user.PostUserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Repository
public class MenuDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public MenuDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public long createMenu(PostMenuRequest postMenuRequest) {
        String sql = "insert into menu(storeId, category, name, price, menuPictureUrl) " +
                "values(:storeId, :category, :name, :price, :menuPictureUrl)";

        SqlParameterSource param = new BeanPropertySqlParameterSource(postMenuRequest);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, param, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public boolean hasInvalidMenuPrice(int price) {
        // 가격이 음수이면 무조건 유효하지 않음
        return price < 0;
    }

    public int modifyPrice(long menuId, int price) {
        String sql = "update menu set price=:price where menuId=:menuId";
        Map<String, Object> param = Map.of(
                "price", price,
                "menuId", menuId);
        return jdbcTemplate.update(sql, param);
    }
    public List<GetMenuResponse> getMenus(String name, String category, String price) {
        String sql = "select name, category, price, menuPictureUrl, status from menu ";

        Map<String, Object> param = Map.of(
                "name", "%" + name + "%",
                "category", "%" + category + "%",
                "price", price);

        return jdbcTemplate.query(sql, param,
                (rs, rowNum) -> new GetMenuResponse(
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getInt("price"),
                        rs.getString("menuPictureUrl"),
                        rs.getString("status"))
        );
    }

    public int modifyMenuStatus_deleted(long menuId) {
        String sql = "update menu set status=:status where menuId=:menuId";
        Map<String, Object> param = Map.of(
                "status", "deleted",
                "menuId", menuId);
        return jdbcTemplate.update(sql, param);
    }

    public boolean hasDuplicateMenu(String name) {
        String sql = "select exists(select name from menu where name=:name and status in ('일반', 'deleted'))";
        Map<String, Object> param = Map.of("name", name);
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, param, boolean.class));
    }


}
