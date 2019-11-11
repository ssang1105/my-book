package com.supersw.mybook.member.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class MemberJoinRequest {

    private final String id;
    private final String password;

    @JsonCreator
    public MemberJoinRequest(@JsonProperty("username") String id, @JsonProperty("password") String password) {
        this.id = id;
        this.password = password;
    }

}
