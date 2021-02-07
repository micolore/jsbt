package com.kubrick.jsbt.sso.client.listener;


import com.kubrick.jsbt.sso.client.session.LocalSessionMappingStorage;
import com.kubrick.jsbt.sso.client.session.SessionMappingStorage;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author k
 * @version 1.0.0
 * @ClassName LogoutListener
 * @description: TODO
 * @date 2021/2/7 下午3:30
 */
public final class LogoutListener implements HttpSessionListener {

    private static SessionMappingStorage sessionMappingStorage = new LocalSessionMappingStorage();

    public static SessionMappingStorage getSessionMappingStorage() {
        return sessionMappingStorage;
    }

    public void setSessionMappingStorage(SessionMappingStorage sms) {
        sessionMappingStorage = sms;
    }

    @Override
    public void sessionCreated(final HttpSessionEvent event) {
    }

    @Override
    public void sessionDestroyed(final HttpSessionEvent event) {
        final HttpSession session = event.getSession();
        sessionMappingStorage.removeBySessionById(session.getId());
    }
}
