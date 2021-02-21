package com.kubrick.jsbt.rpc.hessian.server;

import java.io.Serializable;
import java.util.Map;

/**
 * @author k
 * @version 1.0.0
 * @ClassName User
 * @description: TODO
 * @date 2021/2/22 上午12:09
 */
public class User implements Serializable {

    private static final long serialVersionUID = -1352128884522619903L;

    private String userName;

    private int age;

    private Map<String, Object> detailData;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Map<String, Object> getDetailData() {
        return detailData;
    }

    public void setDetailData(Map<String, Object> detailData) {
        this.detailData = detailData;
    }

}
