package cn.fintecher.supply.finance.loan.manager.core.company.dao;

import cn.fintecher.supply.finance.loan.manager.common.model.CompanyEnterpriseInfoEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author gonghebin
 * @date 2018/6/25 0025下午 2:40
 */
@Mapper
public interface CompanyEnterpriseInfoDao {

    void saveCompanyEnterpriseInfoEntity(CompanyEnterpriseInfoEntity companyEnterpriseInfoEntity);

    CompanyEnterpriseInfoEntity searchCompanyEnterpriseInfoEntity(Long enterpriseId);

    void updateCompanyEnterpriseInfoEntity(CompanyEnterpriseInfoEntity companyEnterpriseInfoEntity);
}
