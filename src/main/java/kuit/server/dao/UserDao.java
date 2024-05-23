package kuit.server.dao;

import kuit.server.dto.user.GetUserResponse;
import kuit.server.dto.user.PostUserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
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

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public UserDao(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }


    public int modifyUserByEmail(String email, PostUserRequest postUserRequest) {
        String sql = "update user set name=:name, phone=:phone,address=:address,password=:password where email=:email";
        SqlParameterSource param = new BeanPropertySqlParameterSource(postUserRequest);
        return jdbcTemplate.update(sql, param);
    }

    public boolean hasDuplicateEmail(String email) {
        String sql = "select exists(select email from user where email=:email and status in ('active'))";
        Map<String, Object> param = Map.of("email", email);
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, param, boolean.class));
    }

    public boolean hasDuplicateName(String name) {
        String sql = "select exists(select email from user where name=:name and status in ('active'))";
        Map<String, Object> param = Map.of("name", name);
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, param, boolean.class));
    }

    public long createUser(PostUserRequest postUserRequest) {
        String sql = "insert into user(email, password, phone, name, address) " +
                "values(:email, :password, :phone, :name, :address)";

        SqlParameterSource param = new BeanPropertySqlParameterSource(postUserRequest);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, param, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public int modifyUserStatus_inactive(long userId) {
        String sql = "update user set status=:status where userid=:userid";
        Map<String, Object> param = Map.of(
                "status", "Inactive",
                "userid", userId);
        return jdbcTemplate.update(sql, param);
    }

    public int modifyName(long userid, String name) {
        String sql = "update user set name=:name where userid=:userid";
        Map<String, Object> param = Map.of(
                "name", name,
                "userid", userid);
        return jdbcTemplate.update(sql, param);
    }

    public List<GetUserResponse> getUsers(String name, String email, String status) {
        String sql = "select email, phone, name, address, status from user " +
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
                        rs.getString("address"),
                        rs.getString("status"))
        );
    }

    public long getUserIdByEmail(String email) {
        String sql = "select userid from user where email=:email";
        Map<String, Object> param = Map.of("email", email);
        return jdbcTemplate.queryForObject(sql, param, long.class);
    }

    public String getPasswordByUserId(long userId) {
        String sql = "select password from user where userid=:userid and status='active'";
        Map<String, Object> param = Map.of("userid", userId);
        return jdbcTemplate.queryForObject(sql, param, String.class);
    }

}