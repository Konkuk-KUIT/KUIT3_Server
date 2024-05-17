package kuit.server.dao;

import kuit.server.dto.menu.GetMenuResponse;
import kuit.server.dto.menu.PostMenuRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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

  public boolean hasDuplicatedMenuName(String menuName) {
    String sql = "select exists(select name from menu where name=:menuName)";
    Map<String, Object> param = Map.of("menuName", menuName);
    return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, param, boolean.class));
  }

  public long save(long storeId, PostMenuRequest request) {
    String sql = "insert into menu(store_id, name, description, price, created_at, updated_at) " +
      "values(" + storeId + ", :name, :description, :price, now(), now())";

    SqlParameterSource param = new BeanPropertySqlParameterSource(request);
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(sql, param, keyHolder);

    return Objects.requireNonNull(keyHolder.getKey()).longValue();
  }

  public int update(long menuId, PostMenuRequest request) {
    String sql = " update menu set name=:name, description=:description, price=:price, updated_at=now() " +
      " where menu_id=" + menuId;

    SqlParameterSource param = new BeanPropertySqlParameterSource(request);
    KeyHolder keyHolder = new GeneratedKeyHolder();
    return jdbcTemplate.update(sql, param, keyHolder);
  }

  public GetMenuResponse findById(long menuId) {
    String sql = " select menu_id, name, description, price from menu " +
      " where menu_id=:menuId and status=:status ";
    Map<String, Object> param = Map.of(
      "status", "active",
      "menuId", menuId);
    return jdbcTemplate.queryForObject(sql, param, new BeanPropertyRowMapper<>(GetMenuResponse.class));
  }

  public List<GetMenuResponse> getMenuList(long storeId) {
    String sql = "select menu_id, name, description, price from menu " +
      "where store_id=:storeId";
    Map<String, Object> param = Map.of("storeId", storeId);
    return jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<>(GetMenuResponse.class));
  }
}
