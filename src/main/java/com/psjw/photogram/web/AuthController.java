package com.psjw.photogram.web;

import com.psjw.photogram.web.dto.auth.SignupDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller // 1. IoC등록 2. 파일을 리턴하는 컨트롤러
public class AuthController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

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
    public String signup(SignupDto signupDto) { //key=value (x-www-form-urlencoded)
        log.info(signupDto.toString());
        return "auth/signin";
    }
}
