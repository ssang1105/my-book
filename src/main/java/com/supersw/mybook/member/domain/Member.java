package com.supersw.mybook.member.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;

@Slf4j
@ToString
@EqualsAndHashCode
@Getter
@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
public class Member {

    @Id
    private String memberId;
    private String password;

    @Column(name = "reg_ymdt")
    @CreationTimestamp
    private LocalDateTime registeredDateTime;


    public static Member create(String id, String password) {
        return new Member(id, password, LocalDateTime.now());
    }
}
