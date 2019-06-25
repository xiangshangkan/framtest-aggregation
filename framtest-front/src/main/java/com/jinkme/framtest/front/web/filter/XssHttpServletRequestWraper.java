package com.jinkme.framtest.front.web.filter;

import org.apache.commons.lang3.StringEscapeUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @ClassName XssHttpServletRequestWraper
 * @Description Xss脚本转换器
 * @Author zhouhui
 * @Date 2019/6/24 09:18
 * @Version 1.0
 */
public class XssHttpServletRequestWraper extends HttpServletRequestWrapper {

    public XssHttpServletRequestWraper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getHeader(String name) {
        return  StringEscapeUtils.escapeHtml4(super.getHeader(name));
    }

    @Override
    public String getQueryString() {
        return StringEscapeUtils.escapeHtml4(super.getQueryString());
    }

    @Override
    public String getParameter(String name) {
        return  StringEscapeUtils.escapeHtml4(super.getParameter(name));
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        if(values != null) {
            int length = values.length;
            String[] escapeValues = new String[length];
            for(int i = 0; i < length; i++) {
                escapeValues[i] = StringEscapeUtils.escapeHtml4(values[i]);
            }
            return escapeValues;
        }
        return values;
    }
}
