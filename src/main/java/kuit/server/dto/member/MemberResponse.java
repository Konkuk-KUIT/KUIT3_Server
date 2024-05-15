package kuit.server.dto.member;

import kuit.server.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class MemberResponse {
    private Long memberId;
    private String name;
    private String nickname;
    private String password;
    private String phone_num;
    private String email;

    public static MemberResponse of(Member member){
        return new MemberResponse(member.getMemberId(),member.getName(),member.getNickname(),member.getPassword(),member.getPhone_num(),member.getEmail());
    }
}
