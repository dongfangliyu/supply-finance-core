package cn.fintecher.supply.finance.loan.manager.service.register.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.RegisterCompanyForm;
import cn.fintecher.supply.finance.loan.manager.common.form.RegisterUserForm;
import cn.fintecher.supply.finance.loan.manager.common.model.RegisterUserEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.RegisterUserInfoEntity;

/**
 * @author gonghebin
 * @date 2018/6/20 0020下午 2:01
 */
public interface RegisterCompanyUserService {

    Message selectRegisteCurrentStep(String registerCode,String enterpriseType);

    Message createRegisteCompanyUser(RegisterUserForm registerUserForm);

    Message updateRegisteCompanyUser(RegisterUserEntity registerUserEntity);

    Message submitRegisteCompanyBaseInfo(RegisterCompanyForm registerCompanyForm);

    Message submitRegisteCompanyDetailInfo(RegisterCompanyForm registerCompanyForm);

    Message searchRegisteCompanyPrimaryInfo(String registerCode);

    Message resetRegisteCompanyData(String registerCode);

    Message submitRegisteCompany(String registerCode);

    Message showRegisteCompanyHintMsg(String registerCode);

    Message confirmRegisteCompany(String registerCode, int accountAmount);

    Long createCompanyEnterpriseEntity(RegisterUserInfoEntity registerUserInfoEntity);

    void createCompanyEnterpriseInfoEntity(RegisterUserInfoEntity registerUserInfoEntity,Long pid);

    void createCompanyOperationEntity(RegisterUserInfoEntity registerUserInfoEntity,Long pid);

    void createBaseBankInfoEntity(RegisterUserInfoEntity registerUserInfoEntity,Long pid);
}
