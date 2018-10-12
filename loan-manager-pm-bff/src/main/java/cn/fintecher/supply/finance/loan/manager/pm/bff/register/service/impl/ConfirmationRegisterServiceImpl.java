package cn.fintecher.supply.finance.loan.manager.pm.bff.register.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.RegisterCompanyForm;
import cn.fintecher.supply.finance.loan.manager.common.form.RegisterUserForm;
import cn.fintecher.supply.finance.loan.manager.pm.bff.register.feign.FCConfirmationRegisterService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.register.service.ConfirmationRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wuxiaoxing
 * @time 2018/8/29 10:39
 */
@Service
public class ConfirmationRegisterServiceImpl implements ConfirmationRegisterService {

    @Autowired
    private FCConfirmationRegisterService fcConfirmationRegisterService;

    @Override
    public Message createRegisteCompanyUser(RegisterUserForm registerUserForm) {
        return fcConfirmationRegisterService.createRegisteCompanyUser(registerUserForm);
    }

    @Override
    public Message submitRegisteCompanyBaseInfo(RegisterCompanyForm registerCompanyForm) {
        return fcConfirmationRegisterService.submitRegisteCompanyBaseInfo(registerCompanyForm);
    }
}
