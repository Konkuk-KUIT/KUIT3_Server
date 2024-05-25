package kuit.server.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Category {

    private Long category_id;
    private Long store_id;
    private String name;
}
