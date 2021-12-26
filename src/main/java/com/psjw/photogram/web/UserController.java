package com.psjw.photogram.web;

import com.psjw.photogram.config.auth.PrincipalDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {

    @GetMapping("/user/{id}")
    public String profile(@PathVariable Long id){
        return "user/profile";
    }

    @GetMapping("/user/{id}/update")
    public String update(@PathVariable Long id, @AuthenticationPrincipal PrincipalDetails principalDetails){
        System.out.println("세션 정보 : "+principalDetails.getUser());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PrincipalDetails mPrincpalDetails = (PrincipalDetails) authentication.getPrincipal();
        System.out.println("직접 찾은 세션 정보 : "+mPrincpalDetails.getUser());

        return "user/update";
    }
}
