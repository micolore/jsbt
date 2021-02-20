package com.kubrick.jsbt.sso.client.sso.client.session;

import javax.servlet.http.HttpSession;

/**
 * @author k
 * @version 1.0.0
 * @ClassName SessionMappingStorage
 * @description: TODO
 * @date 2021/2/7 下午3:27
 */
public interface SessionMappingStorage {
    /**
     * 删除session通过mappingId
     *
     * @param accessToken
     * @return
     */
    HttpSession removeSessionByMappingId(String accessToken);

    /**
     * 删除session通过id
     *
     * @param sessionId
     */
    void removeBySessionById(String sessionId);

    /**
     * 添加session通过id
     *
     * @param accessToken
     * @param session
     */
    void addSessionById(String accessToken, HttpSession session);
}
