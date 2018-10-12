package cn.fintecher.supply.finance.loan.manager.service.login.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.sys.LoginAdminUser;

/**
 * @author wuxiaoxing
 * @time 2018/7/11 15:19
 */
public interface LoginService {

    Message companyUserToLogin(LoginAdminUser loginAdminUser);

    Message sysUserToLogin(LoginAdminUser loginAdminUser);

    Message refreshToken(String refreshToken);

    Message toLogout(String accessToken);

    Message confirmationCompanyUserToLogin(LoginAdminUser loginAdminUser);
}
