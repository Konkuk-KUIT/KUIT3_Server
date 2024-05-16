package kuit.server.dao;

import kuit.server.domain.Member;
import kuit.server.dto.user.GetUserResponse;
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

@Slf4j
@Repository
public class MemberDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public MemberDao(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    /**
     * 유저 조회
     **/
    public Member findById(long memberId) {
        String sql = "select member_id, name, nickname, password, phone_num, email from member where member_id=:member_id";
        Map<String, Object> param = Map.of("member_id", memberId);
        return jdbcTemplate.queryForObject(sql, param, (rs, rowNum) -> new Member(
                Long.parseLong(rs.getString("member_id")),
                rs.getString("name"),
                rs.getString("nickname"),
                rs.getString("password"),
                rs.getString("phone_num"),
                rs.getString("email")
        ));
    }

    /**
     * 유저 전부 조회
     **/
    public List<Member> findAll() {
        String sql = "select member_id, name, nickname, password, phone_num, email from member";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Member(
                Long.parseLong(rs.getString("member_id")),
                rs.getString("name"),
                rs.getString("nickname"),
                rs.getString("password"),
                rs.getString("phone_num"),
                rs.getString("email")
        ));
    }

    /**
     * 유저 생성
     **/

    public Long createMember(Member member) {

        String sql = "insert into member(member_id, name, nickname, password, phone_num, email) " +
                "values(:memberId, :name, :nickname, :password, :phoneNum, :email)";

        SqlParameterSource param = new BeanPropertySqlParameterSource(member);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, param, keyHolder);

        return 1L;
    }

    /**
     * nickname 변경
     */
    public int modifyNickname(Long userId, String nickname) {
        String sql = "update member set nickname=:nickname where member_id=:user_id";
        Map<String, Object> param = Map.of(
                "nickname", nickname,
                "user_id", userId);
        return jdbcTemplate.update(sql, param);
    }

    /**
     * 전부 변경
     */
    public int modifyAll(Long userId, Member member) {
        String sql = "update member set " +
                "name=:name," +
                "nickname=:nickname," +
                "password=:password," +
                "phone_num=:phone_num," +
                "email=:email " +
                "where member_id=:member_id";
        Map<String, Object> param = Map.of(
                "name",member.getName(),
                "nickname", member.getNickname(),
                "password",member.getPassword(),
                "phone_num",member.getPhoneNum(),
                "email",member.getEmail(),
                "member_id", member.getMemberId());
        return jdbcTemplate.update(sql, param);
    }


    /**
     * member 삭제
     */
    public int delete(Long member_id) {
        String sql = "delete from member where member_id=:member_id";
        Map<String, Object> param = Map.of(
                "member_id", member_id);
        return jdbcTemplate.update(sql, param);
    }
}
