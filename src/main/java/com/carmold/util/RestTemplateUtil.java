package com.carmold.util;

import com.alibaba.fastjson.JSON;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;


public class RestTemplateUtil {

    private static RestTemplate restTemplate = new RestTemplate();

    // @Bean 将restTemplate放到spring容器去
    public static RestTemplate createRestTemplate() {
        // 将restTemplate中的第二个MessageConverters->StringHttpMessageConverter替换掉，
        // 原先的StringHttpMessageConverter编码是ISO-8859-1
        // 替换成用utf-8编码的StringHttpMessageConverter
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return restTemplate;
    }

    public static String post(String url, Object params, HttpHeaders requestHeaders) {
        HttpEntity<String> requestEntity = new HttpEntity<String>(JSON.toJSONString(params), requestHeaders);
        requestHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        ResponseEntity<String> rss = request(url, HttpMethod.POST, requestEntity, requestHeaders);
        return rss.getBody();
    }

    public static String get(String url, HttpHeaders requestHeaders) {
        HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);
        ResponseEntity<String> rss = request(url, HttpMethod.GET, requestEntity, requestHeaders);
        return rss.getBody();
    }

    private static ResponseEntity<String> request(String url, HttpMethod method, HttpEntity<String> requestEntity,
                                                  HttpHeaders requestHeaders) {
        ResponseEntity<String> rss = restTemplate.exchange(url, method, requestEntity, String.class);
        return rss;
    }

}
