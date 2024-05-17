package kuit.server.dao;

import kuit.server.dto.store.PostStoreRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

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

}

