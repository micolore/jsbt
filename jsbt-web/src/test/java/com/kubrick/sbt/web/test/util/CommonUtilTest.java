package com.kubrick.sbt.web.test.util;

import com.kubrick.sbt.web.common.utils.JwtUtil;
import org.junit.Test;

/**
 * @author k
 * @version 1.0.0
 * @ClassName CommonUtilTest
 * @description: TODO
 * @date 2021/3/22 下午9:52
 */
public class CommonUtilTest {

    /**
     * 测试jwt
     */
    @Test
    public void testJwt() {
        String token = JwtUtil.buildJWT("1");
        System.out.println(token);
        System.out.println(JwtUtil.checkJWT(token));
        System.out.println(JwtUtil.getJwtID(token));
    }
}
