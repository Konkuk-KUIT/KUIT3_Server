package kuit.server.mydao;

import kuit.server.mydto.member.PostMemberReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Repository
public class MemberDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public MemberDao(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public long createMember(PostMemberReq postMemberReq) {
        log.info("MemberDao.createMember");
        String sql = "insert into user(email, password, phone_number, nickname, profile_image) " +
                "values(:email, :password, :phoneNumber, :nickname, :profileImage)";

        SqlParameterSource param = new BeanPropertySqlParameterSource(postMemberReq);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, param, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    //회원 가입 관련
    public boolean hasDuplicateEmail(String email) {
        log.info("MemberDao.hasDuplicateEmail");
        String sql = "select exists(select email from user where email = :email)";
        Map<String, Object> param = Map.of("email,", email);
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, param, boolean.class));
    }

    public boolean hasDuplicateNickName(String nickname) {
        log.info("MemberDao.hasDuplicateNickName");
        String sql = "select exists(select email from user where nickname = :nickname)";
        Map<String, Object> param = Map.of("nickname", nickname);
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, param, boolean.class));
    }

    //로그인 관련
    public long getUserIdByEmail(String email) {
        log.info("MemberDao.getUserIdByEmail");
        String sql = "select user_id from user where email=:email";
        Map<String, Object> param = Map.of("email", email);
        return jdbcTemplate.queryForObject(sql, param, long.class);
    }
    public String getPasswordByUserId(long userId) {
        log.info("MemberDao.getPasswordByUserId");
        String sql = "select password from user where user_id = :userId";
        Map<String, Object> param = Map.of("userId", userId);
        return jdbcTemplate.queryForObject(sql, param, String.class);
    }

    //회원정보 수정 관련
    public int updateUserEmail(long userId, String email) {
        log.info("MemberDao.updateUserEmail");
        String sql = "update user set email = :email where user_id = :userId";
        Map<String, Object> param = Map.of("userId", userId, "email", email);
        return jdbcTemplate.update(sql, param);
    }

    public int updateUserPassword(long userId, String password) {
        log.info("MemberDao.updateUserPassword");
        String sql = "update user set password = :password where user_id = :userId";
        Map<String, Object> param = Map.of("password", password, "userId", userId);
        return jdbcTemplate.update(sql, param);
    }
}


