package cn.fintecher.supply.finance.loan.manager.common.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 登录用户
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginAdminUser implements Serializable {

	private static final long serialVersionUID = -7445141618522333492L;
	
	// 用户名
	@ApiModelProperty(value = "登录名", required = true)
	private String username;
	// 密码
	@ApiModelProperty(value = "密码", required = true)
	private String password;
	// 验证码
	@ApiModelProperty(value = "验证码")
	private String verifyCode;

	@ApiModelProperty(value = "验证码对应的uuid")
	private String uuid;
//
//	@ApiModelProperty(value = "登录类型(1:pc,2:android,3:android pad,4:ios)", required = true)
//	private String type;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
//	public String getType() {
//		return type;
//	}
//	public void setType(String type) {
//		this.type = type;
//	}

	@Override
	public String toString() {
		return "LoginUser [username=" + username + ", password=" + password + ", verifyCode=" + verifyCode
				+ ", uuid=" + uuid + "]";
	}
}
