package com.kubrick.jsbt.sso.client.sso.client.session;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author k
 * @version 1.0.0
 * @ClassName LocalSessionMappingStorage
 * @description: TODO
 * @date 2021/2/7 下午3:27
 */
public class LocalSessionMappingStorage implements SessionMappingStorage {

    private final Map<String, HttpSession> tokenSessionMap = new HashMap<>();
    private final Map<String, String> sessionTokenMap = new HashMap<>();

    @Override
    public synchronized void addSessionById(final String accessToken, final HttpSession session) {
        sessionTokenMap.put(session.getId(), accessToken);
        tokenSessionMap.put(accessToken, session);
    }

    @Override
    public synchronized void removeBySessionById(final String sessionId) {
        final String accessToken = sessionTokenMap.get(sessionId);
        tokenSessionMap.remove(accessToken);
        sessionTokenMap.remove(sessionId);
    }

    @Override
    public synchronized HttpSession removeSessionByMappingId(final String accessToken) {
        final HttpSession session = tokenSessionMap.get(accessToken);
        if (session != null) {
            removeBySessionById(session.getId());
        }
        return session;
    }
}
