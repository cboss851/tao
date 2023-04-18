package com.tao.space.filter;

import com.tao.commons.utils.StringUtils;
import org.springframework.util.StreamUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Map;

public class MyRequestWrapper extends HttpServletRequestWrapper {

    private final String body;

    public MyRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        this.body = getBodyString(request);
    }

    public String getBody() {
        return body;
    }

    public String getBodyString(final HttpServletRequest request) throws IOException {
        String contentType = request.getContentType();
        String bodyString = "";
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isNotBlank(contentType) && (contentType.contains("multipart/form-data") || contentType.contains("x-www-form-urlencoded"))) {
            Map<String, String[]> parameterMap = request.getParameterMap();
            for (Map.Entry<String, String[]> next : parameterMap.entrySet()) {
                String[] values = next.getValue();
                String value = null;
                if (values != null) {
                    if (values.length == 1) {
                        value = values[0];
                    } else {
                        value = Arrays.toString(values);
                    }
                }
                sb.append(next.getKey()).append("=").append(value).append("&");
            }
            if (sb.length() > 0) {
                bodyString = sb.toString().substring(0, sb.toString().length() - 1);
            }
            return bodyString;
        } else {
            return StreamUtils.copyToString(request.getInputStream(), Charset.forName("UTF-8"));
        }
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream bais = new ByteArrayInputStream(body.getBytes());

        return new ServletInputStream() {

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public int read() {
                return bais.read();
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }
        };
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

}
