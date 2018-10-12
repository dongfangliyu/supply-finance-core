package cn.fintecher.supply.finance.loan.manager.common.response;

import lombok.Data;

import java.io.Serializable;

/**
 * 登录返回参数
 * 
 */
@Data
public class LoginResponse extends CommonResponse<Object> implements Serializable {
	private TokenResponse tokenResponse;
}
