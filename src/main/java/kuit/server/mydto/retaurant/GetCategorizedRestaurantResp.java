package kuit.server.mydto.retaurant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetCategorizedRestaurantResp {

    private List<CategorizedRestaurantEntity> categorizedRestaurants;
    private boolean hasNextEntity;
    private long lastId;
}
