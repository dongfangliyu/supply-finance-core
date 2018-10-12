package cn.fintecher.supply.finance.loan.manager.service.register.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.RegisterCompanyForm;
import cn.fintecher.supply.finance.loan.manager.common.form.RegisterUserForm;

/**
 * @author wuxiaoxing
 * @time 2018/8/29 10:38
 */
public interface ConfirmationRegisterService {
    Message createRegisteCompanyUser(RegisterUserForm registerUserForm);

    Message submitRegisteCompanyBaseInfo(RegisterCompanyForm registerCompanyForm);

}
