package com.kubrick.jsbt.rpc.hessian.client;

import com.caucho.hessian.client.HessianProxyFactory;
import com.kubrick.jsbt.rpc.hessian.server.HelloService;
import com.kubrick.jsbt.rpc.hessian.server.User;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author k
 * @version 1.0.0
 * @ClassName HessianClient
 * @description: TODO
 * @date 2021/2/22 上午12:11
 */
public class HessianClient {

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(10);

        try {
            String url = "http://localhost:8080/hessionDemo/hessian";
            System.out.println("请求的服务端地址：" + url);

            HessianProxyFactory factory = new HessianProxyFactory();
            HelloService helloService = (HelloService) factory.create(HelloService.class, url);
            System.out.println("服务端返回结果为：" + helloService.helloWorld("kitty!"));


            HashMap<String, Object> detailData = new HashMap<String, Object>();
            detailData.put("isMarried", "N");
            detailData.put("gender", "F");

            User user = new User();
            user.setAge(18);
            user.setUserName("OYY");
            user.setDetailData(detailData);

            int time = 100000;
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < time; i++) {
                es.execute(new Runnable() {

                    @Override
                    public void run() {
                        helloService.getMyInfo(user);
                    }
                });

            }
            System.out.println(time + "次调用耗时:" + (System.currentTimeMillis() - startTime));
            System.out.println("+--获得复杂对象:");
            System.out.println("      +--新年龄:" + helloService.getMyInfo(user).getAge());
            System.out.println("      +--隐私信息:" + helloService.getMyInfo(user).getDetailData());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




