package kuit.server.dao;

import kuit.server.dto.user.GetUserResponse;
import kuit.server.dto.user.PostUserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Repository
public class UserDao {
    // DB 접근할때 Dao
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public UserDao(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public boolean hasDuplicateEmail(String email) {
        String sql = "select exists(select email from user where email=:email)";
        Map<String, Object> param = Map.of("email", email);
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, param, boolean.class));
    }

    public boolean hasDuplicateNickName(String name) {
        String sql = "select exists(select email from user where name=:name)";
        Map<String, Object> param = Map.of("name", name);
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, param, boolean.class));
    }

    public long createUser(PostUserRequest postUserRequest) {
        String sql = "insert into user(email, password, phone, name) " +
                "values(:email, :password, :phone, :name)";

        SqlParameterSource param = new BeanPropertySqlParameterSource(postUserRequest);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, param, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public int modifyUserStatus_Inactive(long userid) {
        String sql = "update user set status=:status where userid=:userid";
        Map<String, Object> param = Map.of(
                "status", "Inactive",
                "userid", userid);
        return jdbcTemplate.update(sql, param);
    }

    public int modifyUserStatus_deleted(long userid) {
        String sql = "update user set status=:status where userid=:userid";
        Map<String, Object> param = Map.of(
                "status", "Deleted",
                "userid", userid);
        return jdbcTemplate.update(sql, param);
    }

    public int modifyNickname(long userid, String name) {
        String sql = "update user set name=:name where userid=:userid";
        Map<String, Object> param = Map.of(
                "name", name,
                "userid", userid);
        return jdbcTemplate.update(sql, param);
    }

    public List<GetUserResponse> getUsers(String name, String email, String status) {
        String sql = "select email, phone, name, status from user " +
                "where name like :name and email like :email and status=:status";

        Map<String, Object> param = Map.of(
                "name", "%" + name + "%",
                "email", "%" + email + "%",
                "status", status);

        return jdbcTemplate.query(sql, param,
                (rs, rowNum) -> new GetUserResponse(
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("name"),
                        rs.getString("status"))
        );
    }

    public long getUserIdByEmail(String email) {
        String sql = "select userid from user where email=:email and status='active'";
        Map<String, Object> param = Map.of("email", email);
        return jdbcTemplate.queryForObject(sql, param, long.class);
    }

    public String getPasswordByUserId(long userid) {
        String sql = "select password from user where userid=:userid and status='active'";
        Map<String, Object> param = Map.of("userid", userid);
        return jdbcTemplate.queryForObject(sql, param, String.class);
    }

    public int modifyUserInfo(long userid,String name, String email, String phone,String password) {
        String sql = "update user set email=:email, name=:name, phone=:phone, password=:password where userid=:userid";
        MapSqlParameterSource param = (MapSqlParameterSource) Map.of(
                "name", name,
                "email", email,
                "phone", phone,
                "password", password,
                "userid", userid);
        return jdbcTemplate.update(sql, param);
    }
}