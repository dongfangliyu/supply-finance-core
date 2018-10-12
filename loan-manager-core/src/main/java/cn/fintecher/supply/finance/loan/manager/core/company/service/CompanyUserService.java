package cn.fintecher.supply.finance.loan.manager.core.company.service;

import cn.fintecher.supply.finance.loan.manager.common.model.CompanyUserEntity;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/7/3 0003下午 2:11
 */
public interface CompanyUserService {
    CompanyUserEntity findCompanyUserByName(String userName);

    List<CompanyUserEntity> findCompanyUserByNameAndEnterpriseType(String userName, String enterpriseType);
}
