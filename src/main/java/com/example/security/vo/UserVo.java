package com.example.security.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
public class UserVo {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String username;

    private String avatar;

    private String email;

    private String mobilePhoneNumber;

    private String nickname;

    private boolean admin;
}
