package cn.fintecher.supply.finance.loan.manager.core.company.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.company.entity.CustomerEntity;
import cn.fintecher.supply.finance.loan.manager.common.company.request.BlackListFrom;
import cn.fintecher.supply.finance.loan.manager.common.company.request.CustomerFrom;
import cn.fintecher.supply.finance.loan.manager.common.model.BaseBankInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyEnterpriseEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyEnterpriseInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyOperationEntity;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/7/4 0004上午 11:06
 */
public interface CompanyEnterpriseService {
    CompanyEnterpriseEntity searchCompanyEnterpriseEntity(Long enterpriseId);

    CompanyEnterpriseInfoEntity searchCompanyEnterpriseInfoEntity(Long enterpriseId);

    BaseBankInfoEntity searchBaseBankInfo(Long enterpriseId);

    List<CompanyOperationEntity> searchCompanyOperation(Long enterpriseId);

    CompanyOperationEntity searchCompanyLegal(Long enterpriseId);

    List<CompanyEnterpriseEntity> searchCompanyByNameAndEnterpriceType(String name, String enterpriseType);


    /***
     * 线上为前端接口代码
     * ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     * 线下为后端接口代码
     */


    List<CustomerEntity> selectCustomerManagementList(CustomerFrom customerFrom);

    Integer selectCustomerManagementCount(CustomerFrom customerFrom);

    void updateCompanyEnterpriseEntity(CompanyEnterpriseEntity companyEnterpriseEntity);

    void updateCompanyOperationEntity(CompanyOperationEntity entity);

    void updateCompanyEnterpriseInfoEntity(CompanyEnterpriseInfoEntity companyEnterpriseInfoEntity);

    List<CustomerEntity> selectCustomerBlackList(BlackListFrom blackListFrom);

    Integer selectCustomerBlackListCount(BlackListFrom blackListFrom);

    CompanyEnterpriseEntity selectByName(String name);

    Message<List<CompanyEnterpriseEntity>> searchListConfirmationCompany();
}
