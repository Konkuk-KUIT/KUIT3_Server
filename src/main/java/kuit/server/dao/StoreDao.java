package kuit.server.dao;

import kuit.server.dto.store.GetStoreResponse;
import kuit.server.dto.store.PostStoreRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class StoreDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public StoreDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int modifyStoreStatus_deleted(long storeId) {
        String sql = "update store set status=:status where store_id=:store_id";
        Map<String, Object> param = Map.of(
                "status", "deleted",
                "store_id", storeId);
        return jdbcTemplate.update(sql, param);
    }

    public int modifyStoreStatus_dormant(long storeId) {
        String sql = "update store set status=:status where store_id=:store_id";
        Map<String, Object> param = Map.of(
                "status", "dormant",
                "store_id", storeId);
        return jdbcTemplate.update(sql, param);
    }


    public long createStore(PostStoreRequest storeRequest) {
        String sql = "INSERT INTO Store (name, address, food_category)" +
                "VALUES (:name, :address, :foodCategory)";

        BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(storeRequest);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, paramSource, keyHolder);

        return keyHolder.getKey().longValue();
    }

    public GetStoreResponse findStoreById(long storeId) {
        String sql = "SELECT store_id, name, address, food_category, min_delivery_price, status, phone FROM store WHERE store_id = :storeId";
        return jdbcTemplate.queryForObject(sql, Collections.singletonMap("storeId", storeId), (rs, rowNum) -> new GetStoreResponse(
                rs.getLong("store_id"),
                rs.getString("name"),
                rs.getString("address"),
                rs.getString("food_category"),
                rs.getInt("min_delivery_price"),
                rs.getString("status"),
                rs.getString("phone")
        ));
    }

    public List<GetStoreResponse> findAllStores() {
        String sql = "SELECT store_id, name, address, food_category, min_delivery_price, status, phone FROM store";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new GetStoreResponse(
                rs.getLong("store_id"),
                rs.getString("name"),
                rs.getString("address"),
                rs.getString("food_category"),
                rs.getInt("min_delivery_price"),
                rs.getString("status"),
                rs.getString("phone")
        ));
    }
}
