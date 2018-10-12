package cn.fintecher.supply.finance.loan.manager.core.company.dao;

import cn.fintecher.supply.finance.loan.manager.common.model.CompanyUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/6/25 0025下午 2:41
 */
@Mapper
public interface CompanyUserDao {

    void saveCompanyUserEntity(CompanyUserEntity companyUserEntity);

    List<CompanyUserEntity> searchCompanyUserByUserName(String userName);

    List<CompanyUserEntity> findCompanyUserByNameAndEnterpriseType(@Param("userName") String userName,@Param("type") String enterpriseType);
}
