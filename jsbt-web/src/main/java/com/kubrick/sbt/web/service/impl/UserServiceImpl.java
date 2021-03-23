package com.kubrick.sbt.web.service.impl;

import com.kubrick.sbt.web.common.annotation.log.ServiceLog;
import com.kubrick.sbt.web.common.annotation.redis.RedisCache;
import com.kubrick.sbt.web.common.auth.CustomerUserDetails;
import com.kubrick.sbt.web.common.auth.UserDetailServiceImpl;
import com.kubrick.sbt.web.common.utils.JwtTokenUtil;
import com.kubrick.sbt.web.domain.entity.User;
import com.kubrick.sbt.web.mapper.UserMapper;
import com.kubrick.sbt.web.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * @author k
 */
@Slf4j
@RequiredArgsConstructor
@Scope("threadLocalScope")
@Service
public class UserServiceImpl implements UserService {

    private final UserDetailServiceImpl userDetailService;
    private final BCryptPasswordEncoder bcryptPasswordEncoder;
    private final UserMapper userMapper;

    /**
     * 初始化化
     */
    @PostConstruct
    public void init() {
        log.info("UserServiceImpl init...");
    }

    /**
     * 保存用户
     */
    @Override
    public void saveUser(User user) {
        userMapper.insertUser(user);
    }

    @Async
    @ServiceLog(value = "查询用户")
    @RedisCache(key = "'userid:' + #id +':'")
    @Override
    public List<User> queryAll(int id) {

        return userMapper.list();
    }

    @Override
    public void login(String username, String password, String code, HttpServletRequest request) {
        // 获取UserDetails
        UserDetails userDetails = userDetailService.loadUserByUsername(username);
        // 判断用户是否被禁用
        if (userDetails.isEnabled()) {
            // 前端获取的密码通过passwordEncoder与数据库中的密码对比
            if (userDetails != null && bcryptPasswordEncoder.matches(password, userDetails.getPassword())) {
                // 更新security登录用户对象
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                // 将authenticationToken放入spring security全局中
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                // 生成Token
                String token = JwtTokenUtil.createToken(username, "admin");
                // 将token和头部信息存入map中，登录成功后带给前端。
                HashMap<String, String> tokenMap = new HashMap<>();
                tokenMap.put("token", token);
                System.out.println(tokenMap);
            }
            System.out.println("用户名或密码错误");
        }
        System.out.println("该账号也被禁用,请联系管理员!");
    }

}
