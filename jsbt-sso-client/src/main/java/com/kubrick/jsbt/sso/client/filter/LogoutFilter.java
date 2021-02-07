package com.kubrick.jsbt.sso.client.filter;

import com.kubrick.jsbt.sso.client.constant.SsoConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author k
 * @version 1.0.0
 * @ClassName LogoutFilter
 * @description: TODO
 * @date 2021/2/7 下午3:29
 */
public class LogoutFilter extends BaseFilter {

    @Override
    public boolean isAccessAllowed(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String accessToken = getLogoutParam(request);
        if (accessToken != null) {
            destroySession(accessToken);
            return false;
        }
        return true;
    }

    protected String getLogoutParam(HttpServletRequest request) {
        return request.getHeader(SsoConstant.LOGOUT_PARAMETER_NAME);
    }

    private void destroySession(String accessToken) {
        final HttpSession session = getSessionMappingStorage().removeSessionByMappingId(accessToken);
        if (session != null) {
            session.invalidate();
        }
    }
}
