package com.psjw.photogram.web;

import com.psjw.photogram.domain.user.User;
import com.psjw.photogram.handler.ex.CustomValidationException;
import com.psjw.photogram.service.AuthService;
import com.psjw.photogram.web.dto.auth.SignupDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor //final 필드를 DI할때 사용
@Controller // 1. IoC등록 2. 파일을 리턴하는 컨트롤러
public class AuthController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService; //final은 선언시 또는 생성자에서 무조건 생성해야됨

    @GetMapping("/auth/signin")
    public String signinForm() {
        return "auth/signin";
    }

    @GetMapping("/auth/signup")
    public String signupForm() {
        return "auth/signup";
    }

    //회원가입 버튼 -> /auth/siginup -> /auth/signin
    //회원가입 버튼 X -> CSRF토큰 활성화
    @PostMapping("/auth/signup")
    public String signup(@Valid SignupDto signupDto, BindingResult bindingResult) { //key=value (x-www-form-urlencoded)

        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
                System.out.println(error.getField());
                System.out.println(error.getDefaultMessage());
            }
            throw new CustomValidationException("유효성 검사 실패함", errorMap);
        } else {
            //User <- SignupDto
            User user = signupDto.toEntity();
            log.info(user.toString());
            authService.회원가입(user);
            return "auth/signin";
        }

    }
}
