package com.kubrick.jsbt.sso.server.session.local;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.kubrick.jsbt.sso.server.common.AuthContent;
import com.kubrick.jsbt.sso.server.common.ExpirationPolicy;
import com.kubrick.jsbt.sso.server.session.CodeManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * 本地授权码管理
 *
 * @author Joe
 */
@Component
@ConditionalOnProperty(name = "sso.session.manager", havingValue = "local")
public class LocalCodeManager implements CodeManager, ExpirationPolicy {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private Map<String, DummyCode> codeMap = new ConcurrentHashMap<>();

	@Override
	public void create(String code, AuthContent authContent) {
		codeMap.put(code, new DummyCode(authContent, System.currentTimeMillis() + getExpiresIn() * 1000));
	}

	@Override
	public AuthContent validate(String code) {
		DummyCode dc = codeMap.remove(code);
        if (dc == null || System.currentTimeMillis() > dc.expired) {
            return null;
        }
        return dc.authContent;
	}

	@Scheduled(cron = SCHEDULED_CRON)
	@Override
    public void verifyExpired() {
		codeMap.forEach((code, dummyCode) -> {
            if (System.currentTimeMillis() > dummyCode.expired) {
                codeMap.remove(code);
                logger.debug("code : " + code + "已失效");
            }
        });
    }

    private class DummyCode {
    	private AuthContent authContent;
        private long expired; // 过期时间

        public DummyCode(AuthContent authContent, long expired) {
            this.authContent = authContent;
            this.expired = expired;
        }
    }
}
