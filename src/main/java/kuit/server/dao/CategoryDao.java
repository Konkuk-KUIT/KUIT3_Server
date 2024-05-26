package kuit.server.dao;

import kuit.server.dto.category.CategoryResponse;
import kuit.server.dto.category.CategoryStoreResponse;
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

    public List<CategoryStoreResponse> getStores(Long categoryId,int minOrderFee) {
        String sql = "SELECT * FROM store s " +
                "JOIN category c ON s.category_id = c.category_id " +
                "WHERE c.category_id = ? AND s.min_price >= ?";

        return jdbcTemplate.query(sql, new Object[]{categoryId, minOrderFee}, (rs, rowNum) -> {
            CategoryStoreResponse store = new CategoryStoreResponse();
            if(rs.getString("s.status").equals("active")){
                store.setStoreName(rs.getString("store_name"));
                store.setMinOrderFee(rs.getInt("min_price"));
                store.setWorkingTime(rs.getString("working_time"));
                store.setAddressName(rs.getString("address_name"));
                store.setHoliday(rs.getString("holiday"));
                return store;
            }

            return  null;
        }).stream().filter(Objects::nonNull).collect(Collectors.toList());
    }
}
