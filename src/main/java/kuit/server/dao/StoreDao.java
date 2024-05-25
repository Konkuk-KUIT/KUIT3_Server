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

    public long createStore(PostStoreRequest storeRequest) {
        String sql = "INSERT INTO Store (name, address,food_category,type,phone_number)" +
                "VALUES (:name, :address, :foodCategory, :type, :phoneNumber)";

        BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(storeRequest);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, paramSource, keyHolder);

        return keyHolder.getKey().longValue();
    }

    public GetStoreResponse findStoreById(long storeId) {
        String sql = "SELECT store_id, name, address, food_category, type, phone_number FROM Store WHERE store_id = :storeId";
        return jdbcTemplate.queryForObject(sql, Collections.singletonMap("storeId", storeId), (rs, rowNum) -> new GetStoreResponse(
                rs.getLong("store_id"),
                rs.getString("name"),
                rs.getString("address"),
                rs.getString("food_category"),
                rs.getInt("type"),
                rs.getString("phone_number")
        ));
    }

    public List<GetStoreResponse> findAllStores() {
        String sql = "SELECT store_id, name, address, food_category, type, phone_number FROM Store";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new GetStoreResponse(
                rs.getLong("store_id"),
                rs.getString("name"),
                rs.getString("address"),
                rs.getString("food_category"),
                rs.getInt("type"),
                rs.getString("phone_number")
        ));
    }

    public boolean hasDuplicateStoreName(String storename) {
        String sql = "select exists(select name from store where name =:name)";
        Map<String, Object> param = Map.of("name", storename);
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, param, boolean.class));
    }

}

