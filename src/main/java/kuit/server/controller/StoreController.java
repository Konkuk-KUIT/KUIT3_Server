package kuit.server.controller;

import kuit.server.common.response.BaseResponse;
import kuit.server.dto.store.GetStoreResponse;
import kuit.server.dto.store.PostStoreRequest;
import kuit.server.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreController {
    private final StoreService storeService;

    @PostMapping("")
    public BaseResponse<Long> registerStore(@Validated @RequestBody PostStoreRequest storeRequest) {
        long storeId = storeService.resgisterStore(storeRequest);
        return new BaseResponse<>(storeId);
    }

    @GetMapping("")
    public BaseResponse<List<GetStoreResponse>> getAllStores() {
        List<GetStoreResponse> stores = storeService.getAllStores();
        return new BaseResponse<>(stores);
    }

    @GetMapping("/{storeId}")
    public BaseResponse<GetStoreResponse> getStoreById(@PathVariable long storeId) {
        GetStoreResponse storeResponse = storeService.getStoreById(storeId);
        return new BaseResponse<>(storeResponse);
    }
}
