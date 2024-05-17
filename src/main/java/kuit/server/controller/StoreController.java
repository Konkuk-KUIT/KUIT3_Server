package kuit.server.controller;

import kuit.server.common.response.BaseResponse;
import kuit.server.dto.store.PostStoreRequest;
import kuit.server.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreController {
    private final StoreService storeService;

    @PostMapping("")
    public BaseResponse<Long> registerStore(@Validated @RequestBody PostStoreRequest storeRequest){
        long storeId = storeService.resgisterStore(storeRequest);
        return new BaseResponse<>(storeId);
    }
}
