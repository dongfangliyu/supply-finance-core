package cn.fintecher.supply.finance.loan.manager.service.company.service.impl;

import cn.fintecher.supply.finance.loan.manager.common.model.CompanyEnterpriseInfoEntity;
import cn.fintecher.supply.finance.loan.manager.service.company.feign.FCCompanyEnterpriseCore;
import cn.fintecher.supply.finance.loan.manager.service.company.service.CompanyEnterpriseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gonghebin
 * @date 2018/7/4 0004上午 10:44
 */
@Service
public class CompanyEnterpriseInfoServiceImpl implements CompanyEnterpriseInfoService {

    @Autowired
    private FCCompanyEnterpriseCore fcCompanyEnterpriseCore;

    @Override
    public CompanyEnterpriseInfoEntity searchCompanyEnterpriseInfoEntity(Long enterpriseId) {
        return fcCompanyEnterpriseCore.searchCompanyEnterpriseInfoEntity(enterpriseId);
    }
}
