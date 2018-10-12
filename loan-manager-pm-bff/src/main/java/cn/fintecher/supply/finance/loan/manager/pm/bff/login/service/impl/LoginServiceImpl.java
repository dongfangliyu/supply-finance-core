package cn.fintecher.supply.finance.loan.manager.pm.bff.login.service.impl;


import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.sys.LoginAdminUser;
import cn.fintecher.supply.finance.loan.manager.pm.bff.login.feign.FcLoginService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wuxiaoxing
 * @time 2018/7/11 15:19
 */
@Service("LoginService")
public class LoginServiceImpl implements LoginService {
    @Autowired
    private FcLoginService fcLoginService;

    @Override
    public Message refreshToken(String refreshToken) {
        return fcLoginService.refreshToken(refreshToken);
    }

    @Override
    public Message toLogout(String accessToken) {
        return fcLoginService.toLogout(accessToken);
    }

    @Override
    public Message companyUserToLogin(LoginAdminUser loginAdminUser) {
        return fcLoginService.companyUserToLogin(loginAdminUser);
    }

    @Override
    public Message sysUserToLogin(LoginAdminUser loginAdminUser) {
        return fcLoginService.sysUserToLogin(loginAdminUser);
    }

    @Override
    public Message confirmationCompanyUserToLogin(LoginAdminUser loginAdminUser) {
        return fcLoginService.confirmationCompanyUserToLogin(loginAdminUser);
    }
}
