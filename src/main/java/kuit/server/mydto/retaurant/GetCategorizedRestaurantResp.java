package kuit.server.mydto.retaurant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetCategorizedRestaurantResp {
    String name;
    long min_price;
    String category;
    String pic_URL;
}
