package com.carmold.vo;

import javax.annotation.Generated;
import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

@Generated("com.robohorse.robopojogenerator")
public class WechatAccessTokenResponse {

	@JSONField(name="access_token")
	private String accessToken;

	@JSONField(name="refresh_token")
	private String refreshToken;

	@JSONField(name="openid")
	private String openid;

	@JSONField(name="scope")
	private String scope;

	@JSONField(name="expires_in")
	private int expiresIn;

	private Date createTime;

	public void setAccessToken(String accessToken){
		this.accessToken = accessToken;
	}

	public String getAccessToken(){
		return accessToken;
	}

	public void setRefreshToken(String refreshToken){
		this.refreshToken = refreshToken;
	}

	public String getRefreshToken(){
		return refreshToken;
	}

	public void setOpenid(String openid){
		this.openid = openid;
	}

	public String getOpenid(){
		return openid;
	}

	public void setScope(String scope){
		this.scope = scope;
	}

	public String getScope(){
		return scope;
	}

	public void setExpiresIn(int expiresIn){
		this.expiresIn = expiresIn;
	}

	public int getExpiresIn(){
		return expiresIn;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
 	public String toString(){
		return
			"Response{" +
			"access_token = '" + accessToken + '\'' +
			",refresh_token = '" + refreshToken + '\'' +
			",openid = '" + openid + '\'' +
			",scope = '" + scope + '\'' +
			",expires_in = '" + expiresIn + '\'' +
			"}";
		}
}
