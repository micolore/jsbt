package com.kubrick.sbt.es;

import org.apache.lucene.queryparser.classic.QueryParser;
import org.junit.Test;

/**
 * @author k
 * @version 1.0.0
 * @ClassName QueryTest
 * @description: TODO
 * @date 2021/3/25 下午7:46
 */
public class QueryTest {

    /**
     * @符号不转义
     */
    @Test
    public  void testQueryParser(){
        String nickName = QueryParser.escape("sisus@aaa12!-0191*&^^$%#$@!@#1331qq.com").trim().replace(" ","");
        System.out.println(nickName);
    }
}
