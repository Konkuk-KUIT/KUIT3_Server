package kuit.server.dao;

import java.util.List;
import java.util.Map;
import kuit.server.dto.store.GetStoreMenuResponse;
import kuit.server.dto.user.GetUserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class StoreDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public List<GetStoreMenuResponse> getMenus(long storeId) {
        String sql = "select menu.store_id, menu.name, menu.price from store "
                + "join menu on store.store_id = menu.store_id "
                + "where menu.store_id=:storeId";

        Map<String, Object> param = Map.of("storeId", storeId);

        return jdbcTemplate.query(sql, param,
                (rs, rowNum) -> new GetStoreMenuResponse(
                        rs.getString("menu.name"),
                        rs.getString("menu.price"))
        );
    }
}
