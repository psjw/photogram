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
//    public String update(@PathVariable Long id, @AuthenticationPrincipal PrincipalDetails principalDetails, Model model){
    public String update(@PathVariable Long id, @AuthenticationPrincipal PrincipalDetails principalDetails){
        System.out.println("세션 정보 : "+principalDetails.getUser());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PrincipalDetails mPrincpalDetails = (PrincipalDetails) authentication.getPrincipal();
        System.out.println("직접 찾은 세션 정보 : "+mPrincpalDetails.getUser());
//        model.addAttribute("principal", principalDetails.getUser());
        /*
        header.jsp의 tag로 대체 가능
        <!-- 세션정보에 접근가능 var 담을 변수 -->
        <sec:authorize access="isAuthenticated()">
            <sec:authentication property="principal" var ="principal"/>
        </sec:authorize>
         */
        return "user/update";
    }
}
