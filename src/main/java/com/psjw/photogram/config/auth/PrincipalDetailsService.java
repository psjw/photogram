package com.psjw.photogram.config.auth;


import com.psjw.photogram.domain.user.User;
import com.psjw.photogram.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service //IoC
//POST로 요청시 IoC에서 UserDetailsService를 주고 로그인을 진행(Interface이므로 실제 구현한 PrincipalDetailsService가 동작)
public class PrincipalDetailsService implements UserDetailsService {

    // 시큐리티 세션 처리
    //1. Post /auth/signgin 요청
    //2. 시큐리티의 PrincipalDetailsService로 넘김
    //3. PrincipalDetailsService에서 username 존재 여부 확인
    //4-1. 없음 : 나가
    //4-2. 있음 : 리턴한 PrincipalDetails을 세션에 저장 -> 일반적인 세션 영역(key, value)이 아님
    //           -> 세션영역 안에 SecurityContextHolder에 들어감(PrincipalDetails를 Authentication 객체에 넣음)
    //           -> Annotation으로 대체(@AuthenticationPrincipal)하여 접근
    //           -> Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    //           -> PrincipalDetails mPrincpalDetails = (PrincipalDetails) authentication.getPrincipal();

    private final UserRepository userRepository;

    //1. 패스워드는 시큐리티가 알아서 체킹하므로 신경쓸필요 없음.
    //2. 리턴이 잘되면 자동으로 UserDetails 타입을 세션을 만들어줌.
    public PrincipalDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = userRepository.findByUsername(username);
        if(userEntity == null){
            return null;
        }else{
            return new PrincipalDetails(userEntity);
        }
    }
}
