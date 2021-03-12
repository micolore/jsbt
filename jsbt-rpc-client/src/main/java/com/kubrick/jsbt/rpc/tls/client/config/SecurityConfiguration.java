package com.kubrick.jsbt.rpc.tls.client.config;

import io.grpc.CallCredentials;
import net.devh.boot.grpc.client.security.CallCredentialsHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author k
 * @version 1.0.0
 * @ClassName SecurityConfiguration
 * @description: TODO
 * @date 2021/3/10 下午8:22
 */

@Configuration
public class SecurityConfiguration {

    @Value("${auth.username}")
    private String username;

    @Bean
    CallCredentials grpcCredentials() {
        return CallCredentialsHelper.basicAuth(this.username, this.username + "Password");
    }
}
