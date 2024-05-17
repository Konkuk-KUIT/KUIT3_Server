package kuit.server.temp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter //롬복어노테이션 , 기본 생성자 생략 가능
@NoArgsConstructor
@AllArgsConstructor // 모든 필드를 파라미터를 넣을 수 있는 생성자 만듦
public class UserData {
    private String username;
    private int age;
}
