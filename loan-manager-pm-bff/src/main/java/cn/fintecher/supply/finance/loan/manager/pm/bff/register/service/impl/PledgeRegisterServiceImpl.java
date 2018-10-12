package cn.fintecher.supply.finance.loan.manager.pm.bff.register.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.RegisterCompanyForm;
import cn.fintecher.supply.finance.loan.manager.common.form.RegisterUserForm;
import cn.fintecher.supply.finance.loan.manager.common.model.RegisterUserEntity;
import cn.fintecher.supply.finance.loan.manager.pm.bff.register.feign.FCPledgeRegisterService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.register.service.PledgeRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gonghebin
 * @date 2018/8/17 0017上午 9:53
 */
@Service
public class PledgeRegisterServiceImpl implements PledgeRegisterService {

    @Autowired
    private FCPledgeRegisterService fcPledgeRegisterService;


    @Override
    public Message verificationMobile(String userName) {
        return fcPledgeRegisterService.verificationMobile(userName);
    }

    @Override
    public Message createPledgeRegisterUser(RegisterUserForm registerUserForm) {
        return fcPledgeRegisterService.createPledgeRegisterUser(registerUserForm);
    }

    @Override
    public Message updatePledgeRegisterUser(RegisterUserEntity registerUserEntity) {
        return fcPledgeRegisterService.updatePledgeRegisterUser(registerUserEntity);
    }

    @Override
    public Message submitPledgeRegisterBaseInfo(RegisterCompanyForm registerCompanyForm) {
        return fcPledgeRegisterService.submitPledgeRegisterBaseInfo(registerCompanyForm);
    }

    @Override
    public Message submitPledgeRegisterDetailInfo(RegisterCompanyForm registerCompanyForm) {
        return fcPledgeRegisterService.submitPledgeRegisterDetailInfo(registerCompanyForm);
    }

    @Override
    public Message searchPledgeRegisterPrimaryInfo(String registerCode) {
        return fcPledgeRegisterService.searchPledgeRegisterPrimaryInfo(registerCode);
    }

    @Override
    public Message submitSuccess(RegisterCompanyForm registerCompanyForm) {
        return fcPledgeRegisterService.submitSuccess(registerCompanyForm);
    }

}
