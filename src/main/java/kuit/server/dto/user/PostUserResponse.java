package kuit.server.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostUserResponse {

    private long userid;
    private String jwt;

}