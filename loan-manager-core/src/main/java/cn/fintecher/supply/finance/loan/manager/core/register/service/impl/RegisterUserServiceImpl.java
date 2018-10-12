package cn.fintecher.supply.finance.loan.manager.core.register.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.constant.MsgCodeConstant;
import cn.fintecher.supply.finance.loan.manager.common.model.*;
import cn.fintecher.supply.finance.loan.manager.common.response.CompanyPrimaryInfoResponse;
import cn.fintecher.supply.finance.loan.manager.core.company.dao.CompanyEnterpriseDao;
import cn.fintecher.supply.finance.loan.manager.core.company.dao.CompanyEnterpriseInfoDao;
import cn.fintecher.supply.finance.loan.manager.core.company.dao.CompanyOperationDao;
import cn.fintecher.supply.finance.loan.manager.core.company.dao.CompanyUserDao;
import cn.fintecher.supply.finance.loan.manager.core.register.dao.*;
import cn.fintecher.supply.finance.loan.manager.core.register.service.RegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/6/19 0019下午 6:09
 */
@Service
public class RegisterUserServiceImpl implements RegisterUserService {

    @Autowired
    private RegisterUserDao registerUserDao;

    @Autowired
    private RegisterUserInfoDao registerUserInfoDao;

    @Autowired
    private BaseBankInfoDao baseBankInfoDao;

    @Autowired
    private CompanyEnterpriseDao companyEnterpriseDao;

    @Autowired
    private CompanyEnterpriseInfoDao companyEnterpriseInfoDao;

    @Autowired
    private CompanyUserDao companyUserDao;

    @Autowired
    private CompanyOperationDao companyOperationDao;

//    @Override
//    public Message searchRegisteCompanyUser(RegisterUserEntity registerUserEntity) {
//        Message message = new Message();
//        String currentSteps = registerUserDao.searchRegisteCompanyUser(registerUserEntity);
//        message.setMessage(currentSteps);
//        return message;
//    }

    @Override
    public Message<RegisterUserEntity> selectRegisteCurrentStep(String registerCode) {
        Message<RegisterUserEntity> message = new Message();
        RegisterUserEntity userEntity = registerUserDao.selectRegisteCurrentStep(registerCode);
        message.setMessage(userEntity);
        return message;
    }

//    @Override
//    public RegisterUserEntity selectRegisteUserById(Long pid, String registerCode) {
//        return registerUserDao.selectRegisteUserById(pid,registerCode);
//    }

    @Override
    public boolean createRegisteCompanyUser(RegisterUserEntity registerUserEntity) {
        return registerUserDao.createRegisteCompanyUser(registerUserEntity);
    }

    @Override
    public boolean updateRegisteCompanyUser(RegisterUserEntity registerUserEntity) {
        return registerUserDao.updateRegisteCompanyUser(registerUserEntity);
    }

    @Override
    public boolean createRegisteCompanyUserInfo(RegisterUserInfoEntity registerUserInfoEntity) {
        return registerUserInfoDao.createRegisteCompanyUserInfo(registerUserInfoEntity);
    }

    @Override
    public boolean updateRegisteCompanyUserInfo(RegisterUserInfoEntity registerUserInfoEntity) {
        return registerUserInfoDao.updateRegisteCompanyUserInfo(registerUserInfoEntity);
    }

    @Override
    public CompanyPrimaryInfoResponse searchRegisteCompanyPrimaryInfo(String registerCode) {
        return registerUserInfoDao.searchRegisteCompanyPrimaryInfo(registerCode);
    }

    @Override
    public RegisterUserInfoEntity searchRegisteCompanyUserInfoByCode(String registerCode) {
        return registerUserInfoDao.searchRegisteCompanyUserInfoByCode(registerCode);
    }

    @Override
    public Message saveEnterpriseEntity(CompanyEnterpriseEntity enterpriseEntity) {
        Message message = new Message();
        companyEnterpriseDao.saveEnterpriseEntity(enterpriseEntity);
        message.setMessage(enterpriseEntity.getPid());
        return message;
    }

    @Override
    public void saveCompanyEnterpriseInfoEntity(CompanyEnterpriseInfoEntity companyEnterpriseInfoEntity) {
        companyEnterpriseInfoDao.saveCompanyEnterpriseInfoEntity(companyEnterpriseInfoEntity);
    }

    @Override
    public void saveCompanyOperationEntity(CompanyOperationEntity companyOperationEntity) {
        companyOperationDao.saveCompanyOperationEntity(companyOperationEntity);
    }

    @Override
    public void saveBaseInfoEntity(BaseBankInfoEntity baseBankInfoEntity) {
        baseBankInfoDao.saveBaseInfoEntity(baseBankInfoEntity);
    }

    @Override
    public void saveCompanyUserEntity(CompanyUserEntity companyUserEntity) {
        companyUserDao.saveCompanyUserEntity(companyUserEntity);
    }

    @Override
    public List<RegisterUserEntity> searchRegisterUserByUserName(String userName) {
        return registerUserDao.searchRegisterUserByUserName(userName);
    }

    @Override
    public CompanyUserEntity searchCompanyUserByUserName(String userName) {
        CompanyUserEntity companyUserEntity = new CompanyUserEntity();
        List<CompanyUserEntity> companyUserEntities = companyUserDao.searchCompanyUserByUserName(userName);
        if (companyUserEntities.size() > 0){
            companyUserEntity = companyUserEntities.get(0);
            return companyUserEntity;
        }
        return null;
    }

    @Override
    public CompanyEnterpriseEntity searchCompanyByName(String name) {
        return companyEnterpriseDao.searchCompanyByName(name);
    }

    @Override
    public RegisterUserInfoEntity searchRegisteCompanyByName(String name) {
        return registerUserInfoDao.searchRegisteCompanyByName(name);
    }

    @Override
    public RegisterUserEntity searchRegisteCompanyUserByCode(String registerCode) {
        return registerUserDao.searchRegisteCompanyUserByCode(registerCode);
    }

    @Override
    public RegisterUserInfoEntity searchRegisterUserInfoByPid(String pid) {
        return registerUserInfoDao.searchRegisterUserInfoByPid(pid);
    }

    @Override
    public RegisterUserEntity searchRegisterUserByUserNameLast(String userName) {
        return registerUserDao.searchRegisterUserByUserNameLast(userName);
    }

}
