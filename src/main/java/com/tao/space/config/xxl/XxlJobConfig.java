package com.tao.space.config.xxl;

import com.tao.commons.utils.StringUtils;
import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "xxl.job")
@Data
public class XxlJobConfig {

    private String adminAddresses;

    private String appName;

    private String ip;

    private int port;

    private String accessToken;

    private String logPath;

    private int logRetentionDays;


    @Bean
    public XxlJobSpringExecutor xxlJobExecutor() {
        if (StringUtils.isNotBlank(adminAddresses)) {
            XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
            xxlJobSpringExecutor.setAdminAddresses(adminAddresses);
            xxlJobSpringExecutor.setAppname(appName);
            xxlJobSpringExecutor.setIp(ip);
            xxlJobSpringExecutor.setPort(port);
            xxlJobSpringExecutor.setAccessToken(accessToken);
            xxlJobSpringExecutor.setLogPath(logPath);
            xxlJobSpringExecutor.setLogRetentionDays(logRetentionDays);
            return xxlJobSpringExecutor;
        } else {
            return null;
        }
    }
}