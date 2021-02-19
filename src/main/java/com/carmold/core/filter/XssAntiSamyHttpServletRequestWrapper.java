package com.carmold.core.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.ValueFilter;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.Charset;

public class XssAntiSamyHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private boolean isUpload = false;

    public XssAntiSamyHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        String contentType = request.getContentType();
        if (null != contentType && contentType.toLowerCase().indexOf("multipart/form-data") != -1) {
            isUpload = true;
        }
    }

    @Override
    public String getHeader(String name) {
        return super.getHeader(name);
    }

    @Override
    public String getQueryString() {
        return clean(super.getQueryString());
    }

    @Override
    public String getParameter(String name) {
        return clean(super.getParameter(name));
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        if (values != null) {
            int length = values.length;
            String[] escapseValues = new String[length];
            for (int i = 0; i < length; i++) {
                escapseValues[i] = clean(values[i]);
            }
            return escapseValues;
        }
        return super.getParameterValues(name);
    }

    @Override
    public ServletInputStream getInputStream() {
        try {
            if (isUpload) {
                return super.getInputStream();
            } else {
                final ByteArrayInputStream inputStream = new ByteArrayInputStream(
                        inputHandlers(super.getInputStream()).getBytes("utf-8"));
                return new ServletInputStream() {
                    @Override
                    public int read() throws IOException {
                        return inputStream.read();
                    }

                    @Override
                    public boolean isFinished() {
                        return false;
                    }

                    @Override
                    public boolean isReady() {
                        return false;
                    }

                    @Override
                    public void setReadListener(ReadListener listener) {
                    }
                };
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    public String inputHandlers(ServletInputStream servletInputStream) {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(servletInputStream, Charset.forName("UTF-8")));
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (servletInputStream != null) {
                try {
                    servletInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return clean(sb.toString());
    }

    private static boolean mayBeJSON(String string) {
        return string != null && ("null".equals(string) || string.startsWith("[") && string.endsWith("]")
                || string.startsWith("{") && string.endsWith("}"));
    }

    public String clean(String content) {

        if (content != null && content.length() > 0 && XssAntiSamyHttpServletRequestWrapper.mayBeJSON(content)) {
            try {
                Object object = JSON.parse(content);
                content = JSON.toJSONString(object, new ValueFilter() {
                    @Override
                    public Object process(Object object, String name, Object value) {
                        if (value != null && !XssAntiSamyHttpServletRequestWrapper.mayBeJSON(value.toString())) {
                            return XssAntiSamyHttpServletRequestWrapper.this.clean(value.toString());
                        }
                        return value;
                    }
                });
            } catch (Exception e) {
            }
        } else {
            //return XssUtils.clean(content);
        }

        return content;
    }

}
