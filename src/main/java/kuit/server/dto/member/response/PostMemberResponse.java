package kuit.server.dto.member.response;

import kuit.server.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostMemberResponse {
    private Long memberId;
    private String name;
    private String nickname;
    private String password;
    private String phone_num;
    private String email;

    public static PostMemberResponse of(Member member){
        return new PostMemberResponse(member.getMemberId(),member.getName(),member.getNickname(),member.getPassword(),member.getPhone_num(),member.getEmail());
    }
}
