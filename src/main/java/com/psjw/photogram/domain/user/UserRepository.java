package com.psjw.photogram.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName : com.psjw.photogram.domain.user
 * fileName : UserRepository
 * author : psjw
 * date : 2021-12-25
 * description :
 * ===========================================================
 * DATE              AUTHOR          NOTE
 * -----------------------------------------------------------
 * 2021-12-25        psjw         최초 생성
 */
// 어노테이션이 없어도 IoC등록이 자동으로 된다.
public interface UserRepository extends JpaRepository<User, Long> {
}
