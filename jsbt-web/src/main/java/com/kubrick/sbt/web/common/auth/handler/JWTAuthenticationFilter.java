package com.kubrick.sbt.web.common.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kubrick.sbt.web.common.utils.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author k
 * @version 1.0.0
 * @ClassName JWTAuthenticationFilter
 * @description: TODO
 * @date 2021/3/22 下午11:51
 */
@Slf4j
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String url = request.getRequestURI();
        String header = request.getHeader(JwtUtil.AUTHORIZATION);
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String, Object> map = new HashMap<>();

        if (StringUtils.hasText(header) || !header.startsWith(JwtUtil.TOKEN_PREFIX)) {
            map.put("codeCheck", false);
            map.put("msg", "Token为空");
            String result = objectMapper.writeValueAsString(map);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(result);
            return;
        }
        try {
            UsernamePasswordAuthenticationToken authentication = getAuthentication(request, response);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            map.put("status", "-2");
            map.put("codeCheck", false);
            map.put("msg", "Token已过期");
            log.error("Token已过期: {} ", e.getMessage(), e);
        } catch (UnsupportedJwtException e) {
            map.put("status", "-3");
            map.put("codeCheck", false);
            map.put("msg", "Token格式错误");
            log.error("Token格式错误: {} ", e.getMessage(), e);
        } catch (MalformedJwtException e) {
            map.put("status", "-4");
            map.put("codeCheck", false);
            map.put("msg", "Token没有被正确构造");
            log.error("Token没有被正确构造: {} ", e.getMessage(), e);
        } catch (SignatureException e) {
            map.put("status", "-5");
            map.put("codeCheck", false);
            map.put("msg", "Token签名失败");
            log.error("签名失败: {} ", e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            map.put("status", "-6");
            map.put("codeCheck", false);
            map.put("msg", "Token非法参数异常");
            log.error("非法参数异常: {} ", e.getMessage(), e);
        } catch (Exception e) {
            map.put("status", "-9");
            map.put("codeCheck", false);
            map.put("msg", "Invalid Token");
            log.error("Invalid Token message:{}", e.getMessage(), e);
        }
        String result = objectMapper.writeValueAsString(map);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(result);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader(JwtUtil.AUTHORIZATION);
        if (token != null) {
            String userName = "";
            try {
                // 解密Token
                userName = JwtUtil.validateToken(token);
                if (StringUtils.hasText(userName)) {
                    return new UsernamePasswordAuthenticationToken(userName, null, new ArrayList<>());
                }
            } catch (ExpiredJwtException e) {
                throw e;
                //throw new TokenException("Token已过期");
            } catch (UnsupportedJwtException e) {
                throw e;
                //throw new TokenException("Token格式错误");
            } catch (MalformedJwtException e) {
                throw e;
                //throw new TokenException("Token没有被正确构造");
            } catch (SignatureException e) {
                throw e;
                //throw new TokenException("签名失败");
            } catch (IllegalArgumentException e) {
                throw e;
                //throw new TokenException("非法参数异常");
            } catch (Exception e) {
                throw e;
                //throw new IllegalStateException("Invalid Token. "+e.getMessage());
            }
            return null;
        }
        return null;
    }

}
