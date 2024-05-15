package kuit.server.dao;

import kuit.server.dto.category.CategoryResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class CategoryDao {
    private final JdbcTemplate jdbcTemplate;

    public CategoryDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<CategoryResponse> getAllCategories() {
        String sql = "SELECT * FROM category";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            CategoryResponse category = new CategoryResponse();
            if(rs.getString("status").equals("active")){
                category.setId(rs.getLong("category_id"));
                category.setName(rs.getString("name"));
                return category;
            }
            return  null;

        }).stream().filter(Objects::nonNull).collect(Collectors.toList());
    }
}
