package cn.fintecher.supply.finance.loan.manager.pm.bff.register.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.RegisterCompanyForm;
import cn.fintecher.supply.finance.loan.manager.common.form.RegisterUserForm;
import cn.fintecher.supply.finance.loan.manager.common.model.RegisterUserEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.RegisterUserInfoEntity;
import cn.fintecher.supply.finance.loan.manager.pm.bff.register.feign.FCRegisterCompanyService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.register.service.RegisterCompanyUserService;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gonghebin
 * @date 2018/6/19 0019下午 2:51
 */
@Service
public class RegisterCompanyUserServiceImpl implements RegisterCompanyUserService {

    @Autowired
    private FCRegisterCompanyService fcRegisterCompanyService;


    @Override
    public Message selectRegisteCurrentStep(String registerCode,String enterpriseType) {
        // 查询步骤
        Message message = fcRegisterCompanyService.selectRegisteCurrentStep(registerCode,enterpriseType);
        return message;
    }

    @Override
    public Message createRegisteCompanyUser(RegisterUserForm registerUserForm) {
        return fcRegisterCompanyService.createRegisteCompanyUser(registerUserForm);
    }

    @Override
    public Message updateRegisteCompanyUser(RegisterUserEntity registerUserEntity) {
        return fcRegisterCompanyService.updateRegisteCompanyUser(registerUserEntity);
    }

    @Override
    public Message submitRegisteCompanyBaseInfo(RegisterCompanyForm registerCompanyForm) {
        return fcRegisterCompanyService.submitRegisteCompanyBaseInfo(registerCompanyForm);
    }

    @Override
    public Message submitRegisteCompanyDetailInfo(RegisterCompanyForm registerCompanyForm) {
        return fcRegisterCompanyService.submitRegisteCompanyDetailInfo(registerCompanyForm);
    }

    @Override
    public Message searchRegisteCompanyPrimaryInfo(String registerCode) {
        return fcRegisterCompanyService.searchRegisteCompanyPrimaryInfo(registerCode);
    }

    @Override
    public Message resetRegisteCompanyData(String registerCode) {
        return fcRegisterCompanyService.resetRegisteCompanyData(registerCode);
    }

    @Override
    public Message submitRegisteCompany(String registerCode) {
        return fcRegisterCompanyService.submitRegisteCompany(registerCode);
    }

    @Override
    public Message showRegisteCompanyHintMsg(String registerCode) {
        return fcRegisterCompanyService.showRegisteCompanyHintMsg(registerCode);
    }

    @Override
    public Message showRegisteCompanyConfirmMsg(String registerCode) {
        return fcRegisterCompanyService.showRegisteCompanyHintMsg(registerCode);
    }

    @Override
    public Message confirmRegisteCompany(String registerCode, int accountAmount) {
        return fcRegisterCompanyService.confirmRegisteCompany(registerCode,accountAmount);
    }

    @Override
    public Message searchRegisteCompanyBaseInfo(String registerCode) {
        return fcRegisterCompanyService.searchRegisteCompanyPrimaryInfo(registerCode);
    }
}
