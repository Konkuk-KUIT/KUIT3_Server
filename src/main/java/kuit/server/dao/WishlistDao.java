package kuit.server.dao;

import kuit.server.dto.user.PostUserRequest;
import kuit.server.dto.wishlist.GetWishlistRequest;
import kuit.server.dto.wishlist.GetWishlistResponse;
import kuit.server.dto.wishlist.PostWishlistRequest;
import kuit.server.dto.wishlist.PostWishlistResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Slf4j
@Repository

public class WishlistDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    public WishlistDao(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public PostWishlistResponse createWishlist(PostWishlistRequest postWishlistRequest) {
        String sql = "insert into wish_list(User_id,Shop_id) " +
                "values(:userId,:shopId)";

        SqlParameterSource param = new BeanPropertySqlParameterSource(postWishlistRequest);
        //KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(sql, param);

        PostWishlistResponse postWishlistResponse = new PostWishlistResponse();
        postWishlistResponse.setMessage("찜 목록에 추가 되었습니다.");
        return postWishlistResponse;
    }


    public List<GetWishlistResponse> findShopsByUserId(GetWishlistRequest getWishlistRequest) {
        String FIND_SHOPS_BY_USER_ID_SQL =
                "SELECT s.Shop_id, s.shop_name " +
                        "FROM wish_list w " +
                        "JOIN Shop s ON w.Shop_id = s.Shop_id " +
                        "WHERE w.User_id = :userId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("userId", getWishlistRequest.getUserId());

        return jdbcTemplate.query(FIND_SHOPS_BY_USER_ID_SQL, params, new ShopRowMapper());
    }

    private static final class ShopRowMapper implements RowMapper<GetWishlistResponse> {
        @Override
        public GetWishlistResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
            GetWishlistResponse shop = new GetWishlistResponse();
            shop.setShopId(rs.getLong("Shop_id"));
            shop.setShopName(rs.getString("shop_name"));
            return shop;
        }

    }
}
