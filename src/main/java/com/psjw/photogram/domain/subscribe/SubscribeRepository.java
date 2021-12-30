package com.psjw.photogram.domain.subscribe;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SubscribeRepository extends JpaRepository<Subscribe, Long>{

    //nativeQuery = true 넣어줘야 작동
    @Modifying//INSERT, UPDATE, DELETE를 네이티브 쿼리로 작성하려면 해당 어노테이션 필요!
    @Query(value="INSERT INTO Subscribe(fromUserId, toUserId, createDate) VALUES(:fromUserId, :toUserId, now())" ,nativeQuery = true)
    int mSubscribe(Long fromUserId, Long toUserId); // 성공 시 변경의 행의개수 만큼 리턴,  실패시 -1

    @Modifying//INSERT, UPDATE, DELETE를 네이티브 쿼리로 작성하려면 해당 어노테이션 필요!
    @Query(value="DELETE FROM Subscribe WHERE fromUserId = :fromUserId AND toUserId = :toUserId" ,nativeQuery = true)
    int mUnSubscribe(Long fromUserId, Long toUserId);// 성공 시 변경의 행의개수 만큼 리턴,  실패시 -1

}
