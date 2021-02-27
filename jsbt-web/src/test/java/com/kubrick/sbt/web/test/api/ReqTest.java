package com.kubrick.sbt.web.test.api;

import com.kubrick.sbt.web.utils.ReqDedupHelper;
import org.junit.Test;

/**
 * @author k
 * @version 1.0.0
 * @ClassName ReqAgainTest
 * @description: TODO
 * @date 2021/2/27 下午9:16
 */
public class ReqTest {

    @Test
    public void again() {
        //两个请求一样，但是请求时间差一秒
        String req = "{\n" +
                "\"requestTime\" :\"20190101120001\",\n" +
                "\"requestValue\" :\"1000\",\n" +
                "\"requestKey\" :\"key\"\n" +
                "}";

        String req2 = "{\n" +
                "\"requestTime\" :\"20190101120002\",\n" +
                "\"requestValue\" :\"1000\",\n" +
                "\"requestKey\" :\"key\"\n" +
                "}";

        //全参数比对，所以两个参数MD5不同
        String dedupMD5 = new ReqDedupHelper().dedupParamMD5(req);
        String dedupMD52 = new ReqDedupHelper().dedupParamMD5(req2);
        System.out.println("req1MD5 = " + dedupMD5 + " , req2MD5=" + dedupMD52);

        //去除时间参数比对，MD5相同
        String dedupMD53 = new ReqDedupHelper().dedupParamMD5(req, "requestTime");
        String dedupMD54 = new ReqDedupHelper().dedupParamMD5(req2, "requestTime");
        System.out.println("req1MD5 = " + dedupMD53 + " , req2MD5=" + dedupMD54);

    }
}
