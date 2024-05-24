package kuit.server.service;

import kuit.server.common.exception.UserException;
import kuit.server.dao.ShopDao;
import kuit.server.dao.UserDao;
import kuit.server.dao.WishlistDao;
import kuit.server.dto.wishlist.GetWishlistRequest;
import kuit.server.dto.wishlist.GetWishlistResponse;
import kuit.server.dto.wishlist.PostWishlistRequest;
import kuit.server.dto.wishlist.PostWishlistResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static kuit.server.common.response.status.BaseExceptionResponseStatus.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class WishlistService {
    final private WishlistDao wishlistDao;
    private final UserDao userDao;
    private final ShopDao shopDao;
    public void validationWishlistPost(PostWishlistRequest wishlistRequest){
        if(!userDao.isExistId(wishlistRequest.getUserId())){
            throw new UserException(INVALID_USER_ID);
        }
        if(!shopDao.isExistId(wishlistRequest.getShopId())){
            throw new UserException(INVALID_SHOP_ID);
        }
    }

    public PostWishlistResponse createWishlist(PostWishlistRequest wishlistRequest){
        validationWishlistPost(wishlistRequest);
        return wishlistDao.createWishlist(wishlistRequest);

    }

    public List<GetWishlistResponse> getWishlist(GetWishlistRequest wishlistRequest){
        if(!userDao.isExistId(wishlistRequest.getUserId())){
            throw new UserException(INVALID_USER_ID);
        }
        return wishlistDao.findShopsByUserId(wishlistRequest);

    }
    //public void deleteWishlist()
}
