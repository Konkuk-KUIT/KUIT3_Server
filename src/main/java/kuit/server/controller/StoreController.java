package kuit.server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kuit.server.common.exception.StoreException;
import kuit.server.common.exception.UserException;
import kuit.server.common.response.BaseResponse;
import kuit.server.dto.store.GetStoreResponse;
import kuit.server.dto.store.PostStoreRequest;
import kuit.server.service.StoreService;
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
@RequestMapping("/stores")

public class StoreController {
    private final StoreService storeService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("")
    public BaseResponse<Long> registerStore(@Validated @RequestBody PostStoreRequest storeRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            log.info("[UserController.modifyUserStatus_delete]");
            System.out.println("storeRequest = " + 1123);
            throw new StoreException(INVALID_USER_VALUE, getErrorMessages(bindingResult));
        }
        long storeId = storeService.resgisterStore(storeRequest);
        return new BaseResponse<>(storeId);
    }

    @PatchMapping("/{storeId}/deleted")
    public BaseResponse<Object> modifyUserStatus_deleted(@PathVariable long storeId) {
        log.info("[UserController.modifyUserStatus_delete]");
        storeService.modifyStoreStatus_deleted(storeId);
        return new BaseResponse<>(null);
    }

    @PatchMapping("/{storeId}/dormant")
    public BaseResponse<Object> modifyStoreStatus_dormant(@PathVariable long storeId) {
        log.info("[StoreController.modifyStoreStatus_dormant]");
        storeService.modifyStoreStatus_dormant(storeId);
        return new BaseResponse<>(null);
    }


    @GetMapping("")
    public BaseResponse<List<GetStoreResponse>> getAllStores(
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(required = false, defaultValue = "") String location,
            @RequestParam(required = false, defaultValue = "Open") String status)
    {
        return new BaseResponse<>(storeService.getAllStores());
    }

    @GetMapping("/{storeId}")
    public BaseResponse<GetStoreResponse> getStoreById(@PathVariable long storeId) {
        GetStoreResponse storeResponse = storeService.getStoreById(storeId);
        return new BaseResponse<>(storeResponse);
    }
}