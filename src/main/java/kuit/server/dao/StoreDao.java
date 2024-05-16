package kuit.server.dao;

import kuit.server.domain.Member;
import kuit.server.domain.Store;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Map;

@Slf4j
@Repository
public class StoreDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public StoreDao(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    /**
     * 상점 조회
     **/
    public Store findById(long store_id) {
        String sql = "select store_id, name, minimum_price, status from store where store_id=:store_id";
        Map<String, Object> param = Map.of("store_id", store_id);
        return jdbcTemplate.queryForObject(sql, param, (rs, rowNum) -> new Store(
                Long.parseLong(rs.getString("store_id")),
                rs.getString("name"),
                Long.parseLong(rs.getString("minimum_price")),
                rs.getString("status")
        ));
    }

    /**
     * 상점 생성
     **/

    public Long createStore(Store store) {

        String sql = "insert into store(store_id, name, minimum_price, status) " +
                "values(:storeId, :name, :minimumPrice, :status)";

        SqlParameterSource param = new BeanPropertySqlParameterSource(store);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, param, keyHolder);

        return 1L;
    }
}
