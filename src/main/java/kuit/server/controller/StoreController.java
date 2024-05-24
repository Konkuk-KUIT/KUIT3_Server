package kuit.server.controller;

import kuit.server.common.exception.StoreException;
import kuit.server.common.response.BaseResponse;
import kuit.server.dto.store.GetStoreResponse;
import kuit.server.dto.store.PostStoreRequest;
import kuit.server.dto.store.PostStoreResponse;
import kuit.server.dto.store.PutStoreRequest;
import kuit.server.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Store;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static kuit.server.common.response.status.BaseExceptionResponseStatus.INVALID_STORE_VALUE;
import static kuit.server.common.response.status.BaseExceptionResponseStatus.STORE_NOT_FOUND;
import static kuit.server.util.BindingResultUtils.getErrorMessages;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreController {

    private final StoreService storeService;

    // 새로운 Store 생성
    @PostMapping("")
    public BaseResponse<PostStoreResponse> createStore(@Validated @RequestBody PostStoreRequest postStoreRequest, BindingResult bindingResult) {

        if (bindingResult.hasErrors()){
            throw new StoreException(INVALID_STORE_VALUE, getErrorMessages(bindingResult));
        }

        return new BaseResponse<>(storeService.createStore(postStoreRequest));
    }

    // Store 조회
    @GetMapping("/{storeId}")
    public BaseResponse<GetStoreResponse> getStoreById(@PathVariable long storeId) {
        GetStoreResponse store = storeService.getStoreById(storeId);
        if (store != null) {
            return new BaseResponse<>(store);
        } else {
            throw new StoreException(STORE_NOT_FOUND);
        }
    }

    // 모든 Store 조회
    @GetMapping("")
    public BaseResponse<List<GetStoreResponse>> getAllStores() {
        List<GetStoreResponse> stores = storeService.getAllStores();
        return new BaseResponse<>(stores);
    }

    // Store 정보 수정
    @PutMapping("/{storeId}")
    public BaseResponse<Void> updateStore(@Validated @PathVariable long storeId, @RequestBody PutStoreRequest putStoreRequest, BindingResult bindingResult) {
        int updatedRows = storeService.updateStore(storeId, putStoreRequest);

        if (bindingResult.hasErrors()){
            throw new StoreException(INVALID_STORE_VALUE, getErrorMessages(bindingResult));
        }

        if (updatedRows > 0) {
            return new BaseResponse<>(null);
        } else {
            throw new StoreException(STORE_NOT_FOUND);
        }
    }

    // Store 삭제
    @DeleteMapping("/{storeId}")
    public BaseResponse<Void> deleteStore(@PathVariable long storeId) {
        int deletedRows = storeService.deleteStore(storeId);
        if (deletedRows > 0) {
            return new BaseResponse<>(null);
        } else {
            throw new StoreException(STORE_NOT_FOUND);
        }
    }
}
