package com.tao.space.service.common;

import com.tao.commons.utils.IdUtils;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * requestId服务,日志使用
 *
 * @Author：cboss
 * @Date：2023/3/26
 */
public class RequestIdService {
    private final static String requestKey = "requestId";

    public static void put() {
        //请求头中已包含,网关设置
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(attributes != null && StringUtils.hasText(attributes.getRequest().getHeader("x-request-id"))) {
            HttpServletRequest request = attributes.getRequest();
            String xRequestId = request.getHeader("x-request-id");
            ThreadContext.put(requestKey, xRequestId);
        }else {
            ThreadContext.put(requestKey, IdUtils.timeID());
        }
    }

    public static String get() {
        return ThreadContext.get(requestKey);
    }

    public static void remove() {
        ThreadContext.remove(requestKey);
    }

}
