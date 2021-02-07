package com.kubrick.jsbt.sso.server.controller;

import com.kubrick.jsbt.sso.client.sso.client.constant.SsoConstant;
import com.kubrick.jsbt.sso.server.constant.AppConstant;
import com.kubrick.jsbt.sso.server.session.AccessTokenManager;
import com.kubrick.jsbt.sso.server.session.TicketGrantingTicketManager;
import com.kubrick.jsbt.sso.server.util.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author k
 * @version 1.0.0
 * @ClassName LogoutController
 * @description: TODO
 * @date 2021/2/7 下午5:23
 */
@Controller
@RequestMapping("/logout")
public class LogoutController {

    @Autowired
    private AccessTokenManager accessTokenManager;
    @Autowired
    private TicketGrantingTicketManager ticketGrantingTicketManager;

    /**
     * 登出
     *
     * @param redirectUri
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String logout(
            @RequestParam(value = SsoConstant.REDIRECT_URI, required = true) String redirectUri,
            HttpServletRequest request, HttpServletResponse response) {
        String tgt = CookieUtils.getCookie(request, AppConstant.TGC);
        if (!StringUtils.isEmpty(tgt)) {
            // 删除登录凭证
            ticketGrantingTicketManager.remove(tgt);
            // 删除凭证Cookie
            CookieUtils.removeCookie(AppConstant.TGC, "/", response);
            // 删除所有tgt对应的调用凭证，并通知客户端登出注销本地session
            accessTokenManager.remove(tgt);
        }
        return "redirect:" + redirectUri;
    }
}
