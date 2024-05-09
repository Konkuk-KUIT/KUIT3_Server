package kuit.server.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostLoginResponse {

    private long userId;
    private String jwt;

}