package com.kubrick.jsbt.sso.server.session;

import com.kubrick.jsbt.sso.client.sso.client.rpc.SsoUser;
import com.kubrick.jsbt.sso.server.common.Expiration;

import java.util.UUID;


/**
 * 登录凭证（TGT）管理抽象
 *
 * @author Joe
 */
public interface TicketGrantingTicketManager extends Expiration {

    /**
     * 登录成功后，根据用户信息生成令牌
     *
     * @param user
     * @return
     */
	default String generate(SsoUser user) {
		String tgt = "TGT-" + UUID.randomUUID().toString().replaceAll("-", "");
		create(tgt, user);
		return tgt;
	}

    /**
     * 登录成功后，根据用户信息生成令牌
     *
     * @param user
     * @return
     */
    void create(String tgt, SsoUser user);

    /**
     * 验证st是否存在且在有效期内，并更新过期时间戳
     *
     * @param tgt
     * @return
     */
    SsoUser get(String tgt);

    /**
     * 移除
     *
     * @param tgt
     */
    void remove(String tgt);
}
