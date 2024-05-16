package kuit.server.controller;

import kuit.server.common.exception.UserException;
import kuit.server.common.response.BaseResponse;
import kuit.server.dto.store.response.GetStoreResponse;
import kuit.server.dto.user.GetUserResponse;
import kuit.server.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;



import static kuit.server.common.response.status.BaseExceptionResponseStatus.INVALID_USER_STATUS;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreController {

    private final StoreService storeService;

    /**
     * 회원 조회
     */
    @GetMapping("/{storeId}")
    public BaseResponse<GetStoreResponse> getUserById(
            @PathVariable long storeId) {
        log.info("[StoreController.getUserById]");
        return new BaseResponse<>(storeService.findStoreResponseById(storeId));

    }
}
