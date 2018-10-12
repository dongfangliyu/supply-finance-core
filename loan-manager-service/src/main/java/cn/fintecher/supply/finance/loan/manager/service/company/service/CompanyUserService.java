package cn.fintecher.supply.finance.loan.manager.service.company.service;

import cn.fintecher.supply.finance.loan.manager.common.model.CompanyUserEntity;

/**
 * @author gonghebin
 * @date 2018/7/3 0003下午 1:31
 */
public interface CompanyUserService {

    CompanyUserEntity findCompanyUserByName(String userName);
}
