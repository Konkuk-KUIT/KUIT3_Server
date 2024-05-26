package kuit.server.dao;

import kuit.server.dto.menu.MenuResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class MenuDao {
    private final JdbcTemplate jdbcTemplate;

    public MenuDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<MenuResponse> getMenusByOrderId(Long orderId) {
        String sql = "SELECT * FROM menu m " +
                "JOIN order_menu om ON m.menu_id = om.menu_id " +
                "LEFT JOIN options os ON m.menu_id = os.menu_id " +
                "WHERE om.order_id = ?";
        return jdbcTemplate.query(sql, new Object[]{orderId}, (rs, rowNum) -> {
            MenuResponse menu = new MenuResponse();
            menu.setMenuId(rs.getLong("menu_id"));
            menu.setMenuName(rs.getString("menu_name"));
            menu.setPrice(rs.getInt("m.price") + rs.getInt("os.price"));
            menu.setOptionName(rs.getString("name"));

            return menu;
        });
    }
}
