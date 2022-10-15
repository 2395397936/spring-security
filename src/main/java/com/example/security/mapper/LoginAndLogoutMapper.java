package com.example.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.security.entity.User;

import java.util.List;

public interface LoginAndLogoutMapper extends BaseMapper<User> {

    List<String> queryPermission(Long id);
}
