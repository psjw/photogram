package com.psjw.photogram.domain.subscribe;


import com.psjw.photogram.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
// FK는 Many쪽에서
// N:N (1:N:1)의 관계에서는 중간테이블 생성
@Table(
        //복합키 생성 fromUser, toUser
        uniqueConstraints = {
                @UniqueConstraint(
                        name="subscribe_uk",
                        //실제 DB의 컬럼명을 적음
                        columnNames = {"fromUserId","toUserId"}
                )
        }
)
public class Subscribe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name="fromUserId")
    @ManyToOne
    private User fromUser;

    @JoinColumn(name="toUserId")
    @ManyToOne
    private User toUser;

    private LocalDateTime createDate;

    @PrePersist //DB에 Insert 되기 직전에 실행
    public void createDate(){
        this.createDate = LocalDateTime.now();
    }
}
