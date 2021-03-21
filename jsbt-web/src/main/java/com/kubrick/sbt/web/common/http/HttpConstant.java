package com.kubrick.sbt.web.common.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author k
 * @version 1.0.0
 * @ClassName HttpConstant
 * @description: TODO
 * @date 2021/3/21 下午10:45
 */
public class HttpConstant {

    public final static String HTTP_HEADER_CONTENT_TYPE_JSON = "application/json;charset=utf-8";

    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
    }

    public static String respForbidden() {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("code", HttpStatus.FORBIDDEN);
            map.put("message", "权限不足！");
            return objectMapper.writeValueAsString(map);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String respUnauthorized(AuthenticationException ex) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("code", HttpStatus.UNAUTHORIZED);
            map.put("message", "身份信息不匹配！");
            if (ex instanceof UsernameNotFoundException || ex instanceof BadCredentialsException) {
                map.put("message", "用户名或密码错误");
            } else if (ex instanceof DisabledException) {
                map.put("message", "账户被禁用");
            } else {
                map.put("message", "登录失败!");
            }
            return objectMapper.writeValueAsString(map);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String respLoginOk(Authentication authentication) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("code", HttpStatus.OK);
            map.put("message", "登陆成功");
            map.put("authentication", authentication);
            return objectMapper.writeValueAsString(map);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String respLogOutOk(Authentication authentication) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("code", HttpStatus.OK);
            map.put("message", "退出成功！");
            map.put("authentication", authentication);
            return objectMapper.writeValueAsString(map);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    public static String respNotLogin() {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("code", HttpStatus.OK);
            map.put("message", "未登陆！");
            return objectMapper.writeValueAsString(map);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
