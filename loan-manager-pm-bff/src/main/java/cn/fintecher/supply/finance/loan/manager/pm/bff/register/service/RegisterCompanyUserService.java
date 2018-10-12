package cn.fintecher.supply.finance.loan.manager.pm.bff.register.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.RegisterCompanyForm;
import cn.fintecher.supply.finance.loan.manager.common.form.RegisterUserForm;
import cn.fintecher.supply.finance.loan.manager.common.model.RegisterUserEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.RegisterUserInfoEntity;
import org.springframework.stereotype.Service;

/**
 * @author gonghebin
 * @date 2018/6/19 0019下午 2:52
 */
public interface RegisterCompanyUserService {
    /**
     * 查询注册当前步骤
     * @param registerCode
     * @return
     */
    Message selectRegisteCurrentStep(String registerCode,String enterpriseType);

    /**
     * 创建企业注册用户
     * @param registerUserForm
     * @return
     */
    Message createRegisteCompanyUser(RegisterUserForm registerUserForm);

    /**
     * 更新企业创建用户
     * @param registerUserEntity
     * @return
     */
    Message updateRegisteCompanyUser(RegisterUserEntity registerUserEntity);

    /**
     * 提交注册基本信息
     * @param registerCompanyForm
     * @return
     */
    Message submitRegisteCompanyBaseInfo(RegisterCompanyForm registerCompanyForm);

    /**
     * 提交注册详细信息
     * @param registerCompanyForm
     * @return
     */
    Message submitRegisteCompanyDetailInfo(RegisterCompanyForm registerCompanyForm);

    /**
     * 查询企业注册主要信息
     * @param registerCode
     * @return
     */
    Message searchRegisteCompanyPrimaryInfo(String registerCode);

    /**
     * 重置企业注册数据
     * @param registerCode
     * @return
     */
    Message resetRegisteCompanyData(String registerCode);

    /**
     * 确认无误，提交认证
     * @param registerCode
     * @return
     */
    Message submitRegisteCompany(String registerCode);

    /**
     * 显示注册企业提示信息
     * @param registerCode
     * @return
     */
    Message showRegisteCompanyHintMsg(String registerCode);

    /**
     * 显示注册企业确认信息
     * @param registerCode
     * @return
     */
    Message showRegisteCompanyConfirmMsg(String registerCode);


    /**
     * 注册企业确认
     * @param registerCode
     * @return
     */
    Message confirmRegisteCompany(String registerCode, int accountAmount);

    Message searchRegisteCompanyBaseInfo(String registerCode);
}
