package kuit.server.dao;

import kuit.server.domain.Member;
import kuit.server.domain.Store;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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
     * 유저 조회
     **/
    public Store findById(long store_id) {
        String sql = "select store_id, name, minimum_price, status from store where store_id=:store_id";
        Map<String, Object> param = Map.of("store_id", store_id);
        return jdbcTemplate.queryForObject(sql, param, (rs, rowNum) -> new Store(
                Long.parseLong(rs.getString("store_id")),
                rs.getString("name"),
                rs.getString("minimum_price"),
                rs.getString("status")
        ));
    }
}
