package com.tao.space.config.auth;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 认证配置
 * @Author：cboss
 * @Date：2023/3/31
 */
@Configuration
@ConfigurationProperties(prefix = "auth")
@Data
public class AuthenticationConfig {
    //秘钥
    public String secret;
    //失效时间，单位秒
    public Integer expires;
}
