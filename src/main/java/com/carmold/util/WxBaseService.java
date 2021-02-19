
package com.carmold.util;

import com.alibaba.fastjson.JSON;
import com.carmold.vo.WechatAccessTokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * FastBootWeixin WxBaseService
 * 用于提供微信的基础服务，获取access_token等，其中的接口都是不需要使用access_token的接口
 * 注意拦截调用异常，如果是token过期，重新获取token并重试
 *
 * @author Guangshan
 * @date 2017/7/23 17:14
 * @since 0.1.2
 */
@Component
public class WxBaseService {

    @Value("${appId}")
    private  String wechatAppId;
    @Value("${appSecret}")
    private  String wechatAppSecret;
    @Value("${jscode2accessToken}")
    private  String jscode2accessToken;
    @Value("${accessToken2AppId}")
    private  String accessToken2AppId;
    public   WechatAccessTokenResponse getAccessToken(String code) {
        RestTemplate restTemplate = RestTemplateUtil.createRestTemplate();
        String result = null;
        String url = jscode2accessToken + "?appid=" + wechatAppId +
                "&secret=" + wechatAppSecret + "&code=" + code + "&grant_type=authorization_code";
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        System.out.println("responseEntity:" + JSON.toJSONString(responseEntity));
        if (responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK) {
            result = responseEntity.getBody();
        }
        return JSON.parseObject(result, WechatAccessTokenResponse.class);
    }
//    public WechatOpenIdResponse getWechatOpenId(String accessToken,String userOpenId){
//        RestTemplate restTemplate = RestTemplateUtil.createRestTemplate();
//        String result = null;
//        String url = accessToken2AppId + "?openid=" + userOpenId +
//                "&access_token=" + accessToken + "&lang=zh_CN";
//        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
//        if (responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK) {
//            result = responseEntity.getBody();
//        }
//        return JSON.parseObject(result, WechatOpenIdResponse.class);
//    }


}
