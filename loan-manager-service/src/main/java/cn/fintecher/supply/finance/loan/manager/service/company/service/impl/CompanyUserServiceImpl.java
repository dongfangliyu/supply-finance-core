package cn.fintecher.supply.finance.loan.manager.service.company.service.impl;

import cn.fintecher.supply.finance.loan.manager.common.model.CompanyUserEntity;
import cn.fintecher.supply.finance.loan.manager.service.company.feign.FCCompanyUserCore;
import cn.fintecher.supply.finance.loan.manager.service.company.service.CompanyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author gonghebin
 * @date 2018/7/3 0003下午 1:32
 */
@Service
public class CompanyUserServiceImpl implements CompanyUserService {

    @Autowired
    private FCCompanyUserCore fcCompanyUserCore;

    @Override
    public CompanyUserEntity findCompanyUserByName(String userName) {
        return fcCompanyUserCore.findCompanyUserByName(userName);
    }
}
