package cn.fintecher.supply.finance.loan.manager.pm.bff.Base.service.impl;

import cn.fintecher.supply.finance.loan.manager.common.model.CompanyUserEntity;
import cn.fintecher.supply.finance.loan.manager.pm.bff.Base.feign.FCompanyUserService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.Base.service.CompanyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author WuTianJuan
 * @Date Created in 9:50 2018/7/4
 */
@Service
public class CompanyUserServiceImpl implements CompanyUserService {

    @Autowired
    private FCompanyUserService fcCompanyUserService;

    public CompanyUserEntity findCompanyUserByName(String userName){
     return fcCompanyUserService.findCompanyUserByName(userName);
    }

}
