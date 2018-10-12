package cn.fintecher.supply.finance.loan.manager.core.company.dao;

import cn.fintecher.supply.finance.loan.manager.common.model.BaseBankInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyOperationEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/6/25 0025下午 2:40
 */
@Mapper
public interface CompanyOperationDao {
    void saveCompanyOperationEntity(CompanyOperationEntity companyOperationEntity);

    List<CompanyOperationEntity> searchCompanyOperation(Long enterpriseId);

    CompanyOperationEntity searchCompanyLegal(Long enterpriseId);

    void updateCompanyOperationEntity(CompanyOperationEntity companyOperationEntity);
}
