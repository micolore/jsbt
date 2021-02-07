package com.kubrick.jsbt.sso.client.session;

import javax.servlet.http.HttpSession;

/**
 * @author k
 * @version 1.0.0
 * @ClassName SessionMappingStorage
 * @description: TODO
 * @date 2021/2/7 下午3:27
 */
public interface SessionMappingStorage {
    HttpSession removeSessionByMappingId(String accessToken);

    void removeBySessionById(String sessionId);

    void addSessionById(String accessToken, HttpSession session);
}
