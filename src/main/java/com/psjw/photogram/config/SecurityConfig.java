package com.psjw.photogram.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity // 해당파일(SecurityConfig.java)로 시큐리티를 활성화
@Configuration //IoC 등록
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder encode(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http); //이것 떄문에 로그인 페이지를 가로챔
        //super 삭제 - 기존 시큐리티가 가지고 있는 기능이 다 비활성화됨
        http.csrf().disable();

        http.authorizeRequests()
                //antMatchers의 주소는 인증이 필요
                .antMatchers("/","/user/**","/image/**","/subscribe/**","/comment/**")
                .authenticated()
                //antMatchers 이외의 요청은 모드 허가
                .anyRequest().permitAll()
                //인증이 필요한 페이지는 로그인 페이지로 이동 후 성공시 /로 이동
                .and()
                .formLogin()
                .loginPage("/auth/signin")
                .defaultSuccessUrl("/");
    }
}
