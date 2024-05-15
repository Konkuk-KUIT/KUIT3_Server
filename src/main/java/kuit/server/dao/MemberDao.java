package kuit.server.dao;

import kuit.server.domain.Member;
import kuit.server.dto.user.PostUserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

@Slf4j
@Repository
public class MemberDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public MemberDao(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    /*
     * 유저 조회
     */
    public Member findById(long memberId) {
        String sql = "select member_id, name, nickname, password, phone_num, email from member where member_id=:member_id";
        Map<String, Object> param = Map.of("member_id", memberId);
        return jdbcTemplate.queryForObject(sql, param, new RowMapper<Member>() {
            @Override
            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Member(
                        Long.parseLong(rs.getString("member_id")),
                        rs.getString("name"),
                        rs.getString("nickname"),
                        rs.getString("password"),
                        rs.getString("phone_num"),
                        rs.getString("email")
                );
            }
        });
    }

    /*
     * 유저 생성
     */

    public Long createMember(Member member) {

        String sql = "insert into member(member_id, name, nickname, password, phone_num, email) " +
                "values(:memberId, :name, :nickname, :password, :phone_num, :email)";

        SqlParameterSource param = new BeanPropertySqlParameterSource(member);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, param, keyHolder);

        return 1L;
    }
}
