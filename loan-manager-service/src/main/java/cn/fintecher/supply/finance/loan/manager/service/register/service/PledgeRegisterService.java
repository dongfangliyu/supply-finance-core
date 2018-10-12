package cn.fintecher.supply.finance.loan.manager.service.register.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.RegisterCompanyForm;
import cn.fintecher.supply.finance.loan.manager.common.form.RegisterUserForm;
import cn.fintecher.supply.finance.loan.manager.common.model.RegisterUserEntity;

/**
 * @author gonghebin
 * @date 2018/8/17 0017下午 3:17
 */
public interface PledgeRegisterService {


    Message verificationMobile(String userName);

    Message createPledgeRegisterUser(RegisterUserForm registerUserForm);

    Message updatePledgeRegisterUser(RegisterUserEntity registerUserEntity);

    Message submitPledgeRegisterBaseInfo(RegisterCompanyForm registerCompanyForm);

    Message submitPledgeRegisterDetailInfo(RegisterCompanyForm registerCompanyForm);

    Message searchPledgeRegisterPrimaryInfo(String registerCode);

    Message submitSuccess(RegisterCompanyForm registerCompanyForm);
}
