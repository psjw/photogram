package com.psjw.photogram.web.api;

import com.psjw.photogram.config.auth.PrincipalDetails;
import com.psjw.photogram.domain.user.User;
import com.psjw.photogram.handler.ex.CustomValidationApiException;
import com.psjw.photogram.service.UserService;
import com.psjw.photogram.web.dto.CMRespDto;
import com.psjw.photogram.web.dto.user.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    @PutMapping("/api/user/{id}")
    public CMRespDto<?> update(@PathVariable Long id,
                               @Valid UserUpdateDto userUpdateDto,
                               BindingResult bindingResult, // 꼭 @Valid가 있는 다음 파라미터에 적어야됨
                               @AuthenticationPrincipal PrincipalDetails principalDetails) {
        System.out.println(userUpdateDto);
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
                System.out.println("============================");
                System.out.println(error.getDefaultMessage());
                System.out.println("============================");
            }
            throw new CustomValidationApiException("유효성 검사에 실패함", errorMap);
        }else{
            User userEntity = userService.회원수정(id, userUpdateDto.toEntity());
            principalDetails.setUser(userEntity); //세션정보 변경
            return new CMRespDto<>(1, "회원수정완료", userEntity);
        }
    }
}
