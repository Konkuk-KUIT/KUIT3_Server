package kuit.server.dto.menu;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetMenuResponse {

    private String menu_name;
    private int price;
    private String menu_description;
    private String menu_status;

}
