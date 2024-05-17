package kuit.server.service;

import kuit.server.dao.ShopDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import kuit.server.dao.ShopDao;
import kuit.server.dto.shop.*;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShopService {
    private final ShopDao shopDAO;


    public List<Shop> getAllShops() {
        return shopDAO.getAllShops();
    }

    public List<Shop> getShopById(long shopId) {
        return shopDAO.getShopById(shopId);
    }

    public List<Shop> getShopsByAddress(String address) {
        return shopDAO.getShopsByAddress(address);
    }
    public List<Shop> getShopsByCategoryAndAddress(String category, String address) {
        return shopDAO.getShopsByCategoryAndAddress(category, address);
    }
    public List<Shop> getShopsByCategory(String category) {
        return shopDAO.getShopsByCategory(category);
    }
    public List<FoodCategory> getAllFoodCategories() {
        return shopDAO.getAllFoodCategories();
    }
}
