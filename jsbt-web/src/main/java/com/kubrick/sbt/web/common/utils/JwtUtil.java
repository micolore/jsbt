package com.kubrick.sbt.web.common.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.crypto.MacProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author k
 * JWT校验工具类
 * iss: jwt签发者;
 * sub: jwt所面向的分组用户;
 * aud: 接收jwt的一方;
 * exp: jwt的过期时间，这个过期时间必须要大于签发时间;
 * nbf: 定义在什么时间之前，该jwt都是不可用的;
 * iat: jwt的签发时间;
 * tid: jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击;
 */
@Slf4j
public class JwtUtil {
    /**
     * 前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";
    /**
     * 表头授权
     */
    public static final String AUTHORIZATION = "Authorization";
    /**
     * JWT 加解密类型
     */
    private static final SignatureAlgorithm JWT_ALG = SignatureAlgorithm.HS256;
    /**
     * JWT 生成密钥使用的密码
     */
    private static final String JWT_RULE = "qowxoasodfwodswoak12oao2mma29kzms92pqsodiedidkwdwqpskldiskjdywjs";
    /**
     * JWT 添加至HTTP HEAD中的前缀
     */
    private static final String JWT_SEPARATOR = "";
    /**
     * 密钥：通过生成 JWT_ALG 和 JWT_RULE 加密生成
     */
    private static final Key SECRET = generateKey(JWT_ALG, JWT_RULE);
    /**
     * 默认jwt所面向的分组用户
     */
    private static final String DEFAULT_SUB = "system";
    /**
     * 过期时间---24 hour
     */
    private static final int EXPIRATION_TIME = 60 * 60 * 24;

    /**
     * 使用JWT默认方式，生成加解密密钥
     *
     * @param alg 加解密类型
     * @return SecretKey
     */
    private static SecretKey generateKey(SignatureAlgorithm alg) {
        return MacProvider.generateKey(alg);
    }

    /**
     * 使用指定密钥生成规则，生成JWT加解密密钥
     *
     * @param alg  加解密类型
     * @param rule 密钥生成规则
     * @return SecretKey
     */
    private static SecretKey generateKey(SignatureAlgorithm alg, String rule) {
        // 将密钥生成键转换为字节数组
        byte[] bytes = Base64.decodeBase64(rule);
        // 根据指定的加密方式，生成密钥
        return new SecretKeySpec(bytes, alg.getJcaName());
    }

    /**
     * 构建JWT
     *
     * @param alg      jwt 加密算法
     * @param sub      jwt 面向的用户
     * @param aud      jwt 接收方
     * @param tid      jwt 唯一身份标识
     * @param iss      jwt 签发者
     * @param nbf      jwt 生效日期时间
     * @param duration jwt 有效时间，单位：秒
     * @return JWT字符串
     */
    private static String buildJWT(SignatureAlgorithm alg, String sub, String aud, String tid, String iss, Date nbf, Integer duration) {
        // jwt的签发时间
        long iat = System.currentTimeMillis();
        // jwt的过期时间，这个过期时间必须要大于签发时间
        long exp = 0;
        if (duration != null) {
            exp = (nbf == null ? (iat + duration * 1000) : (nbf.getTime() + duration * 1000));
        }

        // 获取JWT字符串
        String compact = Jwts.builder()
                .signWith(alg, SECRET) //加密方式
                .setSubject(sub) //说明
                .setAudience(aud) //接收用户
                .setId(tid) //唯一身份标示
                .setIssuer(iss) //签发者信息
                .setNotBefore(nbf)
                .setIssuedAt(new Date(iat)) //签发时间
                .setExpiration(new Date(exp)) //过期时间
                .compact();

        // 在JWT字符串前添加"Bearer "字符串，用于加入"Authorization"请求头
        return JWT_SEPARATOR + compact;
    }

    /**
     * 构建JWT
     *
     * @param sub      jwt 面向的用户
     * @param aud      jwt 接收方
     * @param tid      jwt 唯一身份标识
     * @param iss      jwt 签发者
     * @param nbf      jwt 生效日期时间
     * @param duration jwt 有效时间，单位：秒
     * @return JWT字符串
     */
    public static String buildJWT(String sub, String aud, String tid, String iss, Date nbf, Integer duration) {
        return buildJWT(JWT_ALG, sub, aud, tid, iss, nbf, duration);
    }

    /**
     * 构建JWT
     *
     * @param sub      jwt 面向的用户
     * @param tid      jwt 唯一身份标识，主要用来作为一次性token,从而回避重放攻击
     * @param duration jwt 有效时间，单位：秒
     * @return JWT字符串
     */
    public static String buildJWT(String sub, String tid, Integer duration) {
        return buildJWT(sub, null, tid, null, null, duration);
    }

    /**
     * 构建JWT
     * 使用 UUID 作为 tid 唯一身份标识
     * JWT有效时间 1200 秒，即 20 分钟
     *
     * @param tid jwt 唯一身份标识，主要用来作为一次性token,从而回避重放攻击
     * @return JWT字符串
     */
    public static String buildJWT(String tid) {
        return buildJWT(DEFAULT_SUB, tid, 1200);
    }

    /**
     * 从 token 中获取 JWT 的 payload 部分
     *
     * @param token token
     * @return {@link Claims}
     */
    private static Claims getClaimsFromToken(String token) {
        // 移除 JWT 前的前缀字符串

        token = StringUtils.delete(token, JWT_SEPARATOR);
        // 解析 JWT 字符串
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 判断token是否有效：判断sub是否在一个分组、判断是否过期
     *
     * @param token token
     * @param sub   subject
     * @return {@link Boolean}
     */
    public static Boolean checkJWT(String token, String sub) {
        try {
            Claims claims = getClaimsFromToken(token);
            if (claims == null || !claims.getSubject().equals(sub) ||
                    claims.getExpiration().before(new Date())) {
                return false;
            }
        } catch (ExpiredJwtException e) {
            // 仅仅是token过期异常直接返回false
            return false;
        } catch (Exception e) {
            log.warn("JWT验证出错，错误原因：{}", e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * 判断token是否有效：判断sub是否在一个分组、判断是否过期
     *
     * @param token token
     * @return true or false
     */
    public static Boolean checkJWT(String token) {
        return checkJWT(token, DEFAULT_SUB);
    }

    /**
     * 从 token 中获取 JWT 的 payload 的 id 内容
     *
     * @param token token
     * @return id value
     */
    public static String getJwtID(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            return claims.getId();
        } catch (Exception e) {
            log.warn("JWT验证出错，错误原因：{}", e.getMessage());
            return null;
        }
    }

    public static String generateToken(String userName) {
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        // 设置签发时间
        calendar.setTime(new Date());
        // 设置过期时间
        // 添加秒钟
        calendar.add(Calendar.SECOND, EXPIRATION_TIME);
        Date time = calendar.getTime();
        HashMap<String, Object> map = new HashMap<>();
        //you can put any data in the map
        map.put("username", userName);
        String jwt = Jwts.builder()
                .setClaims(map)
                //签发时间
                .setIssuedAt(now)
                //过期时间
                .setExpiration(time)
                .signWith(SECRET)
                .compact();
        //jwt前面一般都会加Bearer
        return TOKEN_PREFIX + jwt;
    }

    public static String validateToken(String token) {
        try {
            // parse the token.
            Map<String, Object> body = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();
            String userName = body.get("username").toString();
            return userName;
        } catch (ExpiredJwtException e) {
            throw e;
        } catch (UnsupportedJwtException e) {
            throw e;
        } catch (MalformedJwtException e) {
            throw e;
        } catch (SignatureException e) {
            throw e;
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }
}

