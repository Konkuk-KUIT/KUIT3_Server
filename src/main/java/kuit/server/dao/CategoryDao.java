package kuit.server.dao;

import kuit.server.domain.Category;
import kuit.server.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Map;

@Slf4j
@Repository
public class CategoryDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public CategoryDao(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    /**
     * 유저 조회
     **/
    public Category findById(long category_id) {
        String sql = "select category_id, store_id, name from category where category_id=:category_id";
        Map<String, Object> param = Map.of("category_id", category_id);
        return jdbcTemplate.queryForObject(sql, param, (rs, rowNum) -> new Category(
                Long.parseLong(rs.getString("category_id")),
                Long.parseLong(rs.getString("store_id")),
                rs.getString("name")
        ));
    }
}
