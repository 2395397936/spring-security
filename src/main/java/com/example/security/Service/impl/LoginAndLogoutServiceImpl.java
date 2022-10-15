package com.example.security.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.common.utils.Errors;
import com.example.common.utils.R;
import com.example.security.Service.LoginAndLogoutService;
import com.example.security.entity.User;
import com.example.security.mapper.LoginAndLogoutMapper;
import com.example.security.utils.JwtUtil;
import com.example.security.utils.RedisCache;
import com.example.security.vo.UserVo;
import com.example.security.vo.params.LoginParam;
import com.example.security.vo.params.LoginUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LoginAndLogoutServiceImpl implements LoginAndLogoutService {
    @Autowired
    private LoginAndLogoutMapper loginAndLogoutMapper;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    RedisCache redisCache;

    @Override
    public User findUserById(Long authorId) {
        User user = loginAndLogoutMapper.selectById(authorId);
        if (user == null) {
            user = new User();
            user.setNickname("佚名");
        }
        return user;
    }
    public UserVo findUserVoById(Long authorId) {
        User user = loginAndLogoutMapper.selectById(authorId);
        UserVo userVo =new UserVo();
        if (user == null) {
            user = new User();
            user.setNickname("佚名");
        }
        BeanUtils.copyProperties(user,userVo);
        return userVo;
    }

    @Override
    public R login(LoginParam loginParam) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginParam.getUsername(), loginParam.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        if (authenticate == null) {
            return R.fail(Errors.PasswordError.getCode(), Errors.PasswordError.getMsg());
        }
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        redisCache.setCacheObject("login:" + userId, loginUser);
        return R.success(copyUser(loginUser.getUser()), jwt);
    }

    @Override
    public R logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getUser().getId();
        redisCache.deleteObject("login:" + userid);
        return R.success(200, "退出成功");
    }

    @Override
    public R deleteUser(Long id) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("id", id);
        int delete = loginAndLogoutMapper.delete(userQueryWrapper);
        if (delete == 1) {
            return R.success(null, "删除成功");
        } else {
            return R.fail(Errors.NoneUserError.getCode(), Errors.NoneUserError.getMsg());
        }
    }

    private UserVo copyUser(User user){
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user,userVo);
        return userVo;
    }
}
