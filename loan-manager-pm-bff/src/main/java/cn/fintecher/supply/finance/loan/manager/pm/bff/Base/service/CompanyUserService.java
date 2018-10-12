package cn.fintecher.supply.finance.loan.manager.pm.bff.Base.service;

import cn.fintecher.supply.finance.loan.manager.common.model.CompanyUserEntity;

/**
 * @Author WuTianJuan
 * @Date Created in 9:49 2018/7/4
 */
public interface CompanyUserService {

    CompanyUserEntity findCompanyUserByName(String userName);
}
