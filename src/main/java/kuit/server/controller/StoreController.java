package kuit.server.controller;

import kuit.server.common.response.BaseResponse;
import kuit.server.dto.store.GetStoreResponse;
import kuit.server.dto.store.PostStoreRequest;
import kuit.server.dto.store.PostStoreResponse;
import kuit.server.dto.store.PutStoreRequest;
import kuit.server.service.StoreService;
import kuit.server.util.BindingResultUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreController {

    private final StoreService storeService;

    // 새로운 Store 생성
    @PostMapping("")
    public BaseResponse<PostStoreResponse> createStore(@RequestBody PostStoreRequest postStoreRequest, BindingResult bindingResult) {
        return new BaseResponse<>(storeService.createStore(postStoreRequest));
    }

    // Store 조회
    @GetMapping("/{storeId}")
    public ResponseEntity<GetStoreResponse> getStoreById(@PathVariable long storeId) {
        GetStoreResponse store = storeService.getStoreById(storeId);
        if (store != null) {
            return new ResponseEntity<>(store, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 모든 Store 조회
    @GetMapping("")
    public ResponseEntity<List<GetStoreResponse>> getAllStores() {
        List<GetStoreResponse> stores = storeService.getAllStores();
        return new ResponseEntity<>(stores, HttpStatus.OK);
    }

    // Store 정보 수정
    @PutMapping("/{storeId}")
    public ResponseEntity<Void> updateStore(@PathVariable long storeId, @RequestBody PutStoreRequest putStoreRequest) {
        int updatedRows = storeService.updateStore(storeId, putStoreRequest);
        if (updatedRows > 0) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Store 삭제
    @DeleteMapping("/{storeId}")
    public ResponseEntity<Void> deleteStore(@PathVariable long storeId) {
        int deletedRows = storeService.deleteStore(storeId);
        if (deletedRows > 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
