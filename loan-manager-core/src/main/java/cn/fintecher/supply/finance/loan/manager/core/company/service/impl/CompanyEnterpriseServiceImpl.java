package cn.fintecher.supply.finance.loan.manager.core.company.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.company.entity.CustomerEntity;
import cn.fintecher.supply.finance.loan.manager.common.company.request.BlackListFrom;
import cn.fintecher.supply.finance.loan.manager.common.company.request.CustomerFrom;
import cn.fintecher.supply.finance.loan.manager.common.model.BaseBankInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyEnterpriseEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyEnterpriseInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyOperationEntity;
import cn.fintecher.supply.finance.loan.manager.core.company.dao.CompanyEnterpriseDao;
import cn.fintecher.supply.finance.loan.manager.core.company.dao.CompanyEnterpriseInfoDao;
import cn.fintecher.supply.finance.loan.manager.core.company.dao.CompanyOperationDao;
import cn.fintecher.supply.finance.loan.manager.core.company.service.CompanyEnterpriseService;
import cn.fintecher.supply.finance.loan.manager.core.register.dao.BaseBankInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/7/4 0004上午 11:06
 */
@Service
public class CompanyEnterpriseServiceImpl implements CompanyEnterpriseService {

    @Autowired
    private CompanyEnterpriseDao companyEnterpriseDao;

    @Autowired
    private CompanyEnterpriseInfoDao companyEnterpriseInfoDao;

    @Autowired
    private CompanyOperationDao companyOperationDao;

    @Autowired
    private BaseBankInfoDao baseBankInfoDao;


    @Override
    public CompanyEnterpriseEntity searchCompanyEnterpriseEntity(Long enterpriseId) {
        return companyEnterpriseDao.searchCompanyEnterpriseEntity(enterpriseId);
    }

    @Override
    public CompanyEnterpriseInfoEntity searchCompanyEnterpriseInfoEntity(Long enterpriseId) {
        return companyEnterpriseInfoDao.searchCompanyEnterpriseInfoEntity(enterpriseId);
    }

    @Override
    public BaseBankInfoEntity searchBaseBankInfo(Long enterpriseId) {
        return baseBankInfoDao.searchBaseBankInfo(enterpriseId);
    }

    @Override
    public List<CompanyOperationEntity> searchCompanyOperation(Long enterpriseId) {
        return companyOperationDao.searchCompanyOperation(enterpriseId);
    }

    @Override
    public CompanyOperationEntity searchCompanyLegal(Long enterpriseId) {
        return companyOperationDao.searchCompanyLegal(enterpriseId);
    }

    @Override
    public List<CompanyEnterpriseEntity> searchCompanyByNameAndEnterpriceType(String name, String enterpriseType) {
        return companyEnterpriseDao.searchCompanyByNameAndEnterpriceType(name,enterpriseType);
    }


    /***
     * 线上为前端接口代码
     * ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     * 线下为后端接口代码
     */



    @Override
    public List<CustomerEntity> selectCustomerManagementList(CustomerFrom customerFrom) {
        return companyEnterpriseDao.selectCustomerManagementList(customerFrom);
    }

    @Override
    public Integer selectCustomerManagementCount(CustomerFrom customerFrom) {
        return companyEnterpriseDao.selectCustomerManagementCount(customerFrom);
    }

    @Override
    public void updateCompanyEnterpriseEntity(CompanyEnterpriseEntity companyEnterpriseEntity) {
        companyEnterpriseDao.updateCompanyEnterpriseEntity(companyEnterpriseEntity);
    }

    @Override
    public void updateCompanyOperationEntity(CompanyOperationEntity companyOperationEntity) {
        companyOperationDao.updateCompanyOperationEntity(companyOperationEntity);
    }

    @Override
    public void updateCompanyEnterpriseInfoEntity(CompanyEnterpriseInfoEntity companyEnterpriseInfoEntity) {
        companyEnterpriseInfoDao.updateCompanyEnterpriseInfoEntity(companyEnterpriseInfoEntity);
    }

    @Override
    public List<CustomerEntity> selectCustomerBlackList(BlackListFrom blackListFrom) {
        return companyEnterpriseDao.selectCustomerBlackList(blackListFrom);
    }

    @Override
    public Integer selectCustomerBlackListCount(BlackListFrom blackListFrom) {
        return companyEnterpriseDao.selectCustomerBlackListCount(blackListFrom);
    }

    @Override
    public Message<List<CompanyEnterpriseEntity>> searchListConfirmationCompany() {
        return new Message<List<CompanyEnterpriseEntity>>(MessageType.MSG_SUCCESS,"company_core",companyEnterpriseDao.searchListConfirmationCompany());
    }

    @Override
    public CompanyEnterpriseEntity selectByName(String name) {

        return companyEnterpriseDao.searchCompanyByName(name);
    }
}
