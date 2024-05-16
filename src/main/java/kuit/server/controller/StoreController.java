package kuit.server.controller;

import kuit.server.common.exception.UserException;
import kuit.server.common.response.BaseResponse;
import kuit.server.dto.member.request.PostMemberRequest;
import kuit.server.dto.member.response.PostMemberResponse;
import kuit.server.dto.store.request.PostStoreRequest;
import kuit.server.dto.store.response.GetStoreResponse;
import kuit.server.dto.store.response.PostStoreResponse;
import kuit.server.dto.user.GetUserResponse;
import kuit.server.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



import static kuit.server.common.response.status.BaseExceptionResponseStatus.INVALID_USER_STATUS;
import static kuit.server.common.response.status.BaseExceptionResponseStatus.INVALID_USER_VALUE;
import static kuit.server.util.BindingResultUtils.getErrorMessages;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreController {

    private final StoreService storeService;

    /**
     * 상점 조회
     */
    @GetMapping("/{storeId}")
    public BaseResponse<GetStoreResponse> getUserById(
            @PathVariable long storeId) {
        log.info("[StoreController.getUserById]");
        return new BaseResponse<>(storeService.findStoreResponseById(storeId));

    }

    /**
     * 상점 등록
     */
    @PostMapping("")
    public BaseResponse<PostStoreResponse> addStore(@Validated @RequestBody PostStoreRequest postStoreRequest, BindingResult bindingResult) {
        log.info("[StoreController.addStore]");
        if (bindingResult.hasErrors()) {
            throw new UserException(INVALID_USER_VALUE, getErrorMessages(bindingResult));
        }
        return new BaseResponse<>(storeService.createStoreByPostStoreResponse(postStoreRequest));
    }
}
