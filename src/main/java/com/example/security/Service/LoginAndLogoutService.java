package com.example.security.Service;

import com.example.common.utils.R;
import com.example.security.entity.User;
import com.example.security.vo.UserVo;
import com.example.security.vo.params.LoginParam;

public interface LoginAndLogoutService {
    User findUserById(Long authorId);
    UserVo findUserVoById(Long authorId);

    R login(LoginParam loginParam);

    R logout();

    R deleteUser(Long id);
}
