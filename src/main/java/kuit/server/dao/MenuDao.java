package kuit.server.dao;

import kuit.server.dto.menu.PostMenuRequest;
import kuit.server.dto.user.PostUserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

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

    public int modifyPrice(long menuId, int price) {
        String sql = "update menu set price=:price where menuId=:menuId";
        Map<String, Object> param = Map.of(
                "price", price,
                "menuId", menuId);
        return jdbcTemplate.update(sql, param);
    }
}
