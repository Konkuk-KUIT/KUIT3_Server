package kuit.server.mydto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostLoginResp {

    private long userId;
    private String jwt;
}
