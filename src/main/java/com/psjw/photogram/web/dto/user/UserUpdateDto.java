package com.psjw.photogram.web.dto.user;


import com.psjw.photogram.domain.user.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserUpdateDto {
    @NotBlank
    private String name; //필수
    @NotBlank
    private String password; //필수
    private String website;
    private String bio;
    private String phone;
    private String gender;

    // 필수 값이 아닌 경우 Entity로 만들면 위험함
    public User toEntity(){
        return User.builder()
                .name(this.name) // 이름을 기재 안했으면 문제!! Validation 체크
                .password(this.password)//패스워드를 기재 안했으면 공백으로 들어와서 문제 : Validation 체크
                .website(this.website)
                .bio(this.bio)
                .phone(this.phone)
                .gender(this.gender)
                .build();
    }
}
