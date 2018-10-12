package cn.fintecher.supply.finance.loan.manager.core.company.dao;

import cn.fintecher.supply.finance.loan.manager.common.company.entity.CustomerEntity;
import cn.fintecher.supply.finance.loan.manager.common.company.request.BlackListFrom;
import cn.fintecher.supply.finance.loan.manager.common.company.request.CustomerFrom;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyEnterpriseEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/6/25 0025下午 2:40
 */
@Mapper
public interface CompanyEnterpriseDao {
    void saveEnterpriseEntity(CompanyEnterpriseEntity enterpriseEntity);

    CompanyEnterpriseEntity searchCompanyByName(String name);

    CompanyEnterpriseEntity searchCompanyEnterpriseEntity(Long pid);

    List<CompanyEnterpriseEntity> searchCompanyByNameAndEnterpriceType(@Param("name") String name,@Param("enterpriseType") String enterpriseType);

    /***
     * 线上为前端接口代码
     * ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     * 线下为后端接口代码
     */


    List<CustomerEntity> selectCustomerManagementList(CustomerFrom customerFrom);

    Integer selectCustomerManagementCount(CustomerFrom customerFrom);

    void updateCompanyEnterpriseEntity(CompanyEnterpriseEntity companyEnterpriseEntity);


    List<CustomerEntity> selectCustomerBlackList(BlackListFrom blackListFrom);

    Integer selectCustomerBlackListCount(BlackListFrom blackListFrom);


    List<CompanyEnterpriseEntity> searchListConfirmationCompany();
}
