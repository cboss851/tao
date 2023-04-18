package com.tao.space.filter;

import com.tao.space.service.common.RequestIdService;
import com.tao.commons.utils.StringUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.AbstractRequestLoggingFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Slf4j
@WebFilter
public class RequestLogFilter extends AbstractRequestLoggingFilter {

    private static final Pattern DATA_PATTERN = Pattern.compile("\"data\" : [\\s\\S]*");
    private static final String DATA_REPLACEMENT = "\"data\":[已隐藏]}";

    private static final Pattern SIGN_PATTERN = Pattern.compile("\"sign\"[\\s\\S]*\",");
    private static final String SIGN_REPLACEMENT = "\"sign\":已隐藏,";

    private static final Pattern PASSWORD_MIDDLE_PATTERN = Pattern.compile("\"password\"[\\s\\S]*\",");
    private static final String PASSWORD_MIDDLE_REPLACEMENT = "\"password\":已隐藏,";
    private static final Pattern PASSWORD_LATEST_PATTERN = Pattern.compile("\"password\"[\\s\\S]*\"");
    private static final String PASSWORD_LATEST_REPLACEMENT = "\"password\":已隐藏";

    private PathMatcher pathMatcher = new AntPathMatcher();

    @Setter
    private int maxResponseBodyLength = 10240;

    @Getter
    private List<String> allowedMimeTypes = new ArrayList<>();

    @Getter
    private List<String> ignorePaths = new ArrayList<>();

    @Getter
    private List<String> dataHidePaths = new ArrayList<>();

    @Getter
    private List<String> signHidePaths = new ArrayList<>();

    @Getter
    private List<String> passwordHidePaths = new ArrayList<>();

    public RequestLogFilter() {
        super();
        setIncludeQueryString(true);
        setIncludeClientInfo(true);
        setIncludeHeaders(true);
        setIncludePayload(false);
        setMaxPayloadLength(10240);
        setBeforeMessagePrefix("");
        setBeforeMessageSuffix("");
        setAfterMessagePrefix("");
        setAfterMessageSuffix("");
        this.allowedMimeTypes.add("application/json");

//        this.ignorePaths.add("/components/**");
//        this.ignorePaths.add("/v2/**");
//        this.ignorePaths.add("/favicon.ico");
//        this.ignorePaths.add("/boss/assets/img");
//        this.ignorePaths.add("/swagger-resources/**");
//        this.ignorePaths.add("/actuator/**");
//        this.ignorePaths.add("/webjars");
//        this.ignorePaths.add("/*.html");
//        this.ignorePaths.add("/*.js");
//        this.ignorePaths.add("/druid");
//        this.ignorePaths.add("/**/file/uploadFile");
//        this.ignorePaths.add("/mobile/wallet/login/**");
//        this.ignorePaths.add("/mobile/wallet/user/**");

//		this.dataHidePaths.add("/**/*list");
//		this.dataHidePaths.add("/**/*List");
//		this.dataHidePaths.add("/**/*Assets");
//		this.dataHidePaths.add("/**/*detail");
//		this.dataHidePaths.add("/**/toBase64");

//		this.signHidePaths.add("/api/**");

    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
        //logger.info(message);
    }

    private boolean allowedMimeType(String contentTypeString) {
        if (StringUtils.isBlank(contentTypeString)) {
            return false;
        }
        contentTypeString = contentTypeString.toLowerCase();
        return this.allowedMimeTypes.contains(contentTypeString);
    }

    private boolean allowedPath(String path) {
        for (String ignorePath : this.ignorePaths) {
            if (this.pathMatcher.match(ignorePath, path)) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        //logger.info(request.getMethod() + " " + message);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        RequestIdService.put();
        MyRequestWrapper requestWrapper = new MyRequestWrapper(request);
        String payload = requestWrapper.getBody();

        HttpServletResponse responseToUse = response;
        if (!(response instanceof ContentCachingResponseWrapper)) {
            responseToUse = new ContentCachingResponseWrapper(response);
        }

        long start = System.currentTimeMillis();
        super.doFilterInternal(requestWrapper, responseToUse, filterChain);
        logResponse(request, responseToUse, payload, start);

        if (responseToUse instanceof ContentCachingResponseWrapper) {
            ContentCachingResponseWrapper wrapper = (ContentCachingResponseWrapper) responseToUse;
            wrapper.copyBodyToResponse();
        }
        RequestIdService.remove();
    }

    private String getReasonPhrase(int statusCode) {
        HttpStatus httpStatus = httpStatus(statusCode);
        if (httpStatus != null) {
            return httpStatus.getReasonPhrase();
        }
        return "";
    }

    private boolean hideDataPath(String path) {
        for (String dataHidePath : this.dataHidePaths) {
            if (this.pathMatcher.match(dataHidePath, path)) {
                return true;
            }
        }
        return false;
    }

    private boolean hideSignPath(String path) {
        for (String signHidePath : this.signHidePaths) {
            if (this.pathMatcher.match(signHidePath, path)) {
                return true;
            }
        }
        return false;
    }

    private boolean hidePasswordPath(String path) {
        for (String passwordHidePath : this.passwordHidePaths) {
            if (this.pathMatcher.match(passwordHidePath, path)) {
                return true;
            }
        }
        return false;
    }

    private HttpStatus httpStatus(int statusCode) {
        for (HttpStatus status : HttpStatus.values()) {
            if (status.value() == statusCode) {
                return status;
            }
        }
        return null;
    }

    private void logResponse(HttpServletRequest request, HttpServletResponse response, String payload, long start) throws IOException {

        long end = System.currentTimeMillis();

        StringBuilder msg = new StringBuilder();

        msg.append(request.getMethod()).append(" uri=").append(request.getRequestURI());

        if (isIncludeQueryString()) {
            String queryString = request.getQueryString();
            if (queryString != null) {
                msg.append('?').append(queryString);
            }
        }

        msg.append(";status=").append(response.getStatus()).append(" ").append(getReasonPhrase(response.getStatus()));
        msg.append(";executionTime=").append(end - start).append("ms");
        if (StringUtils.isNotBlank(payload)) {
            if (hideSignPath(request.getRequestURI())) {
                payload = SIGN_PATTERN.matcher(payload).replaceFirst(SIGN_REPLACEMENT);
            }
            if (hidePasswordPath(request.getRequestURI())) {
                payload = PASSWORD_MIDDLE_PATTERN.matcher(payload).replaceFirst(PASSWORD_MIDDLE_REPLACEMENT);
                payload = PASSWORD_LATEST_PATTERN.matcher(payload).replaceFirst(PASSWORD_LATEST_REPLACEMENT);
            }
            msg.append("\npayload = ").append(payload);
        }
        String responseBody;
        if (hideDataPath(request.getRequestURI())) {
            responseBody = DATA_PATTERN.matcher(responseBodyAll(response)).replaceFirst(DATA_REPLACEMENT);
        } else {
            responseBody = responseBody(response);
        }
        if (StringUtils.isNotBlank(responseBody)) {
            msg.append("\nbody = ").append(responseBody);
        }
        log.info(msg.toString());
    }

    private String responseBody(HttpServletResponse response) throws IOException {
        ContentCachingResponseWrapper wrapper = WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
        if (wrapper != null) {
            byte[] buf = wrapper.getContentAsByteArray();
            if (buf.length > 0) {
                int length = Math.min(buf.length, this.maxResponseBodyLength);
                String body;
                try {
                    body = new String(buf, 0, length, "utf-8");
                } catch (UnsupportedEncodingException ex) {
                    body = "[unknown]";
                }
                return body;
            }
        }
        return "";
    }

    // 处理分页查询时，隐藏data字段用。
    private String responseBodyAll(HttpServletResponse response) throws IOException {
        ContentCachingResponseWrapper wrapper = WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
        if (wrapper != null) {
            byte[] buf = wrapper.getContentAsByteArray();
            if (buf.length > 0) {
                String body;
                try {
                    body = new String(buf, 0, buf.length, "utf-8");
                } catch (UnsupportedEncodingException ex) {
                    body = "[unknown]";
                }
                return body;
            }
        }
        return "";
    }
}
