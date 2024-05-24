package kuit.server.dao;

import kuit.server.dto.shop.Shop;
import kuit.server.dto.user.GetUserResponse;
import kuit.server.dto.user.PostUserRequest;
import kuit.server.dto.user.address.GetUserAddressResponse;
import kuit.server.dto.user.address.PostUserAddressRequest;
import kuit.server.dto.wishlist.PostWishlistRequest;
import kuit.server.dto.wishlist.PostWishlistResponse;
import lombok.extern.slf4j.Slf4j;
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
import java.util.Map;
import java.util.Objects;

@Slf4j
@Repository
public class UserDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public UserDao(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public boolean hasDuplicateEmail(String email) {
        String sql = "select exists(select email from user where email=:email and status in ('active', 'dormant'))";
        Map<String, Object> param = Map.of("email", email);
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, param, boolean.class));
    }

    public boolean isExistId(long userId) {
        String sql = "select exists(select User_id from user where User_id=:userId)"; //and status in ('active', 'dormant'))";
        Map<String, Object> param = Map.of("userId", userId);
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, param, boolean.class));
    }

    public boolean hasDuplicateNickName(String nickname) {
        String sql = "select exists(select email from user where nickname=:nickname and status in ('active', 'dormant'))";
        Map<String, Object> param = Map.of("nickname", nickname);
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, param, boolean.class));
    }

    public long createUser(PostUserRequest postUserRequest) {
        String sql = "insert into user(email, password, phone_number, nickname, profile_image,status) " +
                "values(:email, :password, :phoneNumber, :nickname, :profileImage, 'active')";

        SqlParameterSource param = new BeanPropertySqlParameterSource(postUserRequest);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, param, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public int modifyUserStatus_dormant(long userId) {
        String sql = "update user set status=:status where user_id=:user_id";
        Map<String, Object> param = Map.of(
                "status", "dormant",
                "user_id", userId);
        return jdbcTemplate.update(sql, param);
    }

    public int modifyUserStatus_deleted(long userId) {
        String sql = "update user set status=:status where user_id=:user_id";
        Map<String, Object> param = Map.of(
                "status", "deleted",
                "user_id", userId);
        return jdbcTemplate.update(sql, param);
    }

    public int modifyNickname(long userId, String nickname) {
        String sql = "update user set nickname=:nickname where user_id=:user_id";
        Map<String, Object> param = Map.of(
                "nickname", nickname,
                "user_id", userId);
        return jdbcTemplate.update(sql, param);
    }

    public List<GetUserResponse> getUsers(String nickname, String email, String status) {
        String sql = "select email, phone_number, nickname, profile_image, status from user " +
                "where nickname like :nickname and email like :email and status=:status";

        Map<String, Object> param = Map.of(
                "nickname", "%" + nickname + "%",
                "email", "%" + email + "%",
                "status", status);

        return jdbcTemplate.query(sql, param,
                (rs, rowNum) -> new GetUserResponse(
                        rs.getString("email"),
                        rs.getString("phone_number"),
                        rs.getString("nickname"),
                        rs.getString("profile_image"),
                        rs.getString("status"))
        );
    }

    public long getUserIdByEmail(String email) {
        String sql = "select user_id from user where email=:email and status='active'";
        Map<String, Object> param = Map.of("email", email);
        return jdbcTemplate.queryForObject(sql, param, long.class);
    }

    public String getPasswordByUserId(long userId) {
        String sql = "select password from user where user_id=:user_id and status='active'";
        Map<String, Object> param = Map.of("user_id", userId);
        return jdbcTemplate.queryForObject(sql, param, String.class);
    }

    public void createUserAddress(PostUserAddressRequest postUserAddressRequest) {
        String sql = "insert into user_address(address_category,user_id,user_address) " +
                "values(:addressCategory, :userId, :userAddress)";

        SqlParameterSource param = new BeanPropertySqlParameterSource(postUserAddressRequest);
        //KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(sql, param);

    }


    public List<GetUserAddressResponse> getUserAddress(long userId) {
        String sql = "SELECT * FROM user_address WHERE user_id = :userId ";
        Map<String, Object> paramMap = Map.of("userId",userId);

        return jdbcTemplate.query(sql, paramMap, (rs, rowNum) -> mapRowToShop(rs));
    }
    public boolean hasDuplicateUserAddress(PostUserAddressRequest postUserAddressRequest){
        String sql = "SELECT COUNT(*) FROM User_address WHERE address_category = :addressCategory AND user_address = :userAddress";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("addressCategory", postUserAddressRequest.getAddressCategory());
        params.addValue("userAddress", postUserAddressRequest.getUserAddress());

        int count = jdbcTemplate.queryForObject(sql, params, Integer.class);

        return count > 0;
    }
    private GetUserAddressResponse mapRowToShop(ResultSet rs) throws SQLException {
        Shop shop = new Shop();
        GetUserAddressResponse userAddressResponse = new GetUserAddressResponse();
        userAddressResponse.setUserAddress(rs.getString("user_address"));
        userAddressResponse.setAddressCategory(rs.getString("address_category"));
        return userAddressResponse;
    }
}