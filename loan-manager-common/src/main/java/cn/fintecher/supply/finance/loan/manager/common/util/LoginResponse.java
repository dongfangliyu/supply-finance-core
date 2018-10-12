package cn.fintecher.supply.finance.loan.manager.common.util;

import cn.fintecher.supply.finance.loan.manager.common.sys.TokenResponse;
import lombok.Data;

import java.io.Serializable;

/**
 * 登录返回参数
 * 
 * @author huanglei
 */
@Data
public class LoginResponse extends CommonResponse<Object> implements Serializable {
	private TokenResponse tokenResponse;
}
