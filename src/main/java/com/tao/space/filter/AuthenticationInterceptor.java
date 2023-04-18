package com.tao.space.filter;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.tao.commons.result.ResponseCodeEnum;
import com.tao.commons.result.ResponseResult;
import com.tao.commons.utils.JWTUtils;
import com.tao.commons.utils.StringUtils;
import com.tao.space.config.auth.AuthenticationConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author：cboss
 * @Date：2023/3/31
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationInterceptor implements HandlerInterceptor {
    private final AuthenticationConfig authenticationConfig;
    private PathMatcher pathMatcher = new AntPathMatcher();
    private List<String> ignorePaths = new ArrayList<>();

    @PostConstruct
    public void init() {
        log.info("初始化AuthenticationInterceptor");
        this.ignorePaths.add("/login");
        this.ignorePaths.add("/v2/**");
        this.ignorePaths.add("/favicon.ico");
        this.ignorePaths.add("/swagger-resources/**");
        this.ignorePaths.add("/actuator/**");
        this.ignorePaths.add("/webjars/**");
        this.ignorePaths.add("/*.html");
        this.ignorePaths.add("/*.js");
        this.ignorePaths.add("/v3/api-docs");
        JWTUtils.SECRET = authenticationConfig.getSecret();
        JWTUtils.EXPIRES = authenticationConfig.getExpires();
    }

    /**
     * 在controller调用之前执行，可以进行编码、安全控制、权限校验等处理
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       if(StringUtils.isBlank(authenticationConfig.getSecret())){
           return true;
       }
        String url = request.getRequestURI();
        log.info("AuthenticationInterceptor start {}", url);
        if (!this.ignorePath(url)) {
            String token = "";
            try {
                //获取请求头中令牌
                token = request.getHeader("token");
                if (StringUtils.isBlank(token)) {
                    log.error("token验证失败,token为空,{}", url);
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                    return false;
                }
                JWTUtils.verify(token);//验证令牌
            } catch (SignatureVerificationException e) {
                log.error("token验证失败,无效签名,{}请求头中的token为{}", url, token);
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            } catch (TokenExpiredException e) {
                log.error("token验证失败,token过期,{}请求头中的token为{}", url, token);
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            } catch (AlgorithmMismatchException e) {
                log.error("token验证失败,token算法不一致,{}请求头中的token为{}", url, token);
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            } catch (Exception e) {
                log.error("token验证失败,token无效,{}请求头中的token为{}", url, token);
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }
        }
        return true;
    }

    private boolean ignorePath(String path) {
        for (String ignorePath : this.ignorePaths) {
            if (this.pathMatcher.match(ignorePath, path)) {
                return true;
            }
        }
        return false;
    }
}