package cn.fintecher.supply.finance.loan.manager.common.sys;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wuxiaoxing
 * @time 2018/5/31 20:24
 */
@Data
public class TokenResponse implements Serializable {
    private  String  access_token;
    private  String  token_type;
    private  String  refresh_token;
    private  String  expires_in;
    private  String  scope;
    private  String error;
    private  String error_description;
    private  Object user;
}
