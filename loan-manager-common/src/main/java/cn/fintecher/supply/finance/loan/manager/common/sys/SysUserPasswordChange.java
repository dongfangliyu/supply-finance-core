package cn.fintecher.supply.finance.loan.manager.common.sys;

import lombok.Data;

/**
 * @author wuxiaoxing
 * @time 2018/5/26 20:43
 */
@Data
public class SysUserPasswordChange {
    private Integer userId;
    private String newPassword;
    private String password;
    private String username;
}
