package cn.fintecher.supply.finance.loan.manager.core.register.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.model.*;
import cn.fintecher.supply.finance.loan.manager.common.response.CompanyPrimaryInfoResponse;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/6/21 0021上午 9:38
 */
public interface RegisterUserService {

//    Message searchRegisteCompanyUser(RegisterUserEntity registerUserEntity);

    Message<RegisterUserEntity> selectRegisteCurrentStep(String registerCode);

//    RegisterUserEntity selectRegisteUserById(Long pid, String registerCode);

    boolean createRegisteCompanyUser(RegisterUserEntity registerUserEntity);

    boolean updateRegisteCompanyUser(RegisterUserEntity registerUserEntity);

    boolean createRegisteCompanyUserInfo(RegisterUserInfoEntity registerUserInfoEntity);

    boolean updateRegisteCompanyUserInfo(RegisterUserInfoEntity registerUserInfoEntity);

    CompanyPrimaryInfoResponse searchRegisteCompanyPrimaryInfo(String registerCode);

    RegisterUserInfoEntity searchRegisteCompanyUserInfoByCode(String registerCode);

    Message saveEnterpriseEntity(CompanyEnterpriseEntity enterpriseEntity);

    void saveCompanyEnterpriseInfoEntity(CompanyEnterpriseInfoEntity companyEnterpriseInfoEntity);

    void saveCompanyOperationEntity(CompanyOperationEntity companyOperationEntity);

    void saveBaseInfoEntity(BaseBankInfoEntity baseBankInfoEntity);

    void saveCompanyUserEntity(CompanyUserEntity companyUserEntity);

    List<RegisterUserEntity> searchRegisterUserByUserName(String userName);

    CompanyUserEntity searchCompanyUserByUserName(String userName);

    CompanyEnterpriseEntity searchCompanyByName(String name);

    RegisterUserInfoEntity searchRegisteCompanyByName(String name);

    RegisterUserEntity searchRegisteCompanyUserByCode(String registerCode);

    RegisterUserInfoEntity searchRegisterUserInfoByPid(String pid);

    RegisterUserEntity searchRegisterUserByUserNameLast(String userName);
}
