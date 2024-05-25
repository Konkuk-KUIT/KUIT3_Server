package kuit.server.dto.member.response;

import kuit.server.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class GetMemberResponse {
    private Long memberId;
    private String name;
    private String nickname;
    private String password;
    private String phoneNum;
    private String email;

    public static GetMemberResponse of(Member member){
        return new GetMemberResponse(member.getMemberId(),member.getName(),member.getNickname(),member.getPassword(),member.getPhoneNum(),member.getEmail());
    }

}
