package kuit.server.dao;

import kuit.server.dto.store.GetStoreResponse;
import kuit.server.dto.store.PatchStoreRequest;
import kuit.server.dto.store.PostStoreRequest;
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
public class StoreDao {

  private final NamedParameterJdbcTemplate jdbcTemplate;

  public StoreDao(DataSource dataSource) {
    this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
  }

  public boolean hasDuplicatedBusinessNumber(String businessNumber) {
    String sql = "select exists(select business_number from store where business_number=:businessNumber)";
    Map<String, Object> param = Map.of("businessNumber", businessNumber);
    return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, param, boolean.class));
  }

  public long save(PostStoreRequest request) {
    String sql = "insert into store(business_number, name, open_time, close_time, min_order_amount, notice_content, description, origin_label, latitude, longitude, street_address, address_detail, created_at, updated_at, status) " +
      "values(:businessNumber, :name, :openTime, :closeTime, :minOrderAmount, :noticeContent, :description, :originLabel, :latitude, :longitude, :streetAddress, :addressDetail, now(), now(), 'active')";

    SqlParameterSource param = new BeanPropertySqlParameterSource(request);
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(sql, param, keyHolder);

    return Objects.requireNonNull(keyHolder.getKey()).longValue();
  }

  public int update(long storeId, PatchStoreRequest request) {
    String sql = " update store set open_time=:openTime, close_time=:closeTime, min_order_amount=:minOrderAmount, notice_content=:noticeContent, description=:description, origin_label=:originLabel, latitude=:latitude, longitude=:longitude, street_address=:streetAddress, address_detail=:addressDetail, updated_at=now() " +
      " where store_id=" + storeId;

    SqlParameterSource param = new BeanPropertySqlParameterSource(request);
    KeyHolder keyHolder = new GeneratedKeyHolder();
    return jdbcTemplate.update(sql, param, keyHolder);
  }

  public GetStoreResponse findById(long storeId) {
    String sql = "select store_id, business_number, name, open_time, close_time, notice_content, description, origin_label, street_address from store" +
      " where store_id=:storeId and status=:status";
    Map<String, Object> param = Map.of(
      "status", "active",
      "storeId", storeId);
    return jdbcTemplate.queryForObject(sql, param, new BeanPropertyRowMapper<>(GetStoreResponse.class));
  }

  public List<GetStoreResponse> getStoreList() {
    String sql = "select store_id, business_number, name, open_time, close_time, notice_content, description, origin_label, street_address from store" +
      " where status=:status limit :limit";
    Map<String, Object> param = Map.of("status", "active", "limit", 10);
    return jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<>(GetStoreResponse.class));
  }

  public boolean validateStoreId(long storeId) {
    String sql = "select exists(select store_id from store where store_id=:storeId and status='active')";
    Map<String, Object> param = Map.of("storeId", storeId);
    return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, param, boolean.class));
  }
}
