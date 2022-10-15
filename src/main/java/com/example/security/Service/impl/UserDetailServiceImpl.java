package com.example.security.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.security.entity.User;
import com.example.security.mapper.LoginAndLogoutMapper;
import com.example.security.vo.params.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private LoginAndLogoutMapper loginAndLogoutMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("username",username);
        User user = loginAndLogoutMapper.selectOne(queryWrapper);
        //TODO 权限查询
        if (user == null) {
            throw new RuntimeException("用户名错误");
        }

        return new LoginUser(user, loginAndLogoutMapper.queryPermission(user.getId()));

    }
}
