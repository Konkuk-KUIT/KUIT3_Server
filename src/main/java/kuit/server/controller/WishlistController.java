package kuit.server.controller;

import kuit.server.common.exception.UserException;
import kuit.server.common.response.BaseResponse;
import kuit.server.dto.shop.PostShopResponse;
import kuit.server.dto.wishlist.GetWishlistRequest;
import kuit.server.dto.wishlist.GetWishlistResponse;
import kuit.server.dto.wishlist.PostWishlistRequest;
import kuit.server.dto.wishlist.PostWishlistResponse;

import kuit.server.service.WishlistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static kuit.server.common.response.status.BaseExceptionResponseStatus.INVALID_USER_VALUE;
import static kuit.server.util.BindingResultUtils.getErrorMessages;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/wishlist")
public class WishlistController {
    private final WishlistService wishlistService;
    @PostMapping("")
    public BaseResponse<PostWishlistResponse> getWishlist(@Validated @RequestBody PostWishlistRequest wishLisRreq, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            throw new RuntimeException( getErrorMessages(bindingResult));
        }
        return new BaseResponse<>(wishlistService.createWishlist(wishLisRreq));
    }
    @GetMapping("")
    public BaseResponse<Object> getWishlist(@Validated @RequestBody GetWishlistRequest wishLisRreq, BindingResult bindingResult){

        return new BaseResponse<>(wishlistService.getWishlist(wishLisRreq));
    }
}
