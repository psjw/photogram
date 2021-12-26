package com.psjw.photogram.config.auth;

import com.psjw.photogram.domain.user.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * packageName : com.psjw.photogram.config.auth
 * fileName : PricipalDetails
 * author : psjw
 * date : 2021-12-26
 * description :
 * ===========================================================
 * DATE              AUTHOR          NOTE
 * -----------------------------------------------------------
 * 2021-12-26        psjw         최초 생성
 */
@Data
public class PrincipalDetails implements UserDetails {

    private static final long serialVersionUID = -7686554113595198236L;

    private User user;

    public PrincipalDetails(User user) {
        this.user = user;
    }

    //권한을 가져옴
    //권한 : 한개가 아닐 수 있음.(3개 이상의 권한)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collector = new ArrayList<>();
        /*
        collector.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });
         */

        collector.add(() -> user.getRole());

        return collector;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    //계정만료여부
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    //계정잠김 여부
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //비밀번호 변경 여부
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //계정 활성화 여부
    @Override
    public boolean isEnabled() {
        return true;
    }
}
