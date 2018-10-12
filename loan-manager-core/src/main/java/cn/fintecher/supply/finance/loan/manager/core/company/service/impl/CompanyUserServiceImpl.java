package cn.fintecher.supply.finance.loan.manager.core.company.service.impl;

import cn.fintecher.supply.finance.loan.manager.common.model.CompanyUserEntity;
import cn.fintecher.supply.finance.loan.manager.core.company.dao.CompanyUserDao;
import cn.fintecher.supply.finance.loan.manager.core.company.service.CompanyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/7/3 0003下午 2:11
 */
@Service
public class CompanyUserServiceImpl implements CompanyUserService {

    @Autowired
    private CompanyUserDao companyUserDao;

    @Override
    public CompanyUserEntity findCompanyUserByName(String userName) {
        CompanyUserEntity companyUserEntity = new CompanyUserEntity();
        List<CompanyUserEntity> companyUserEntities = companyUserDao.searchCompanyUserByUserName(userName);
        if (companyUserEntities.size()>0){
            companyUserEntity = companyUserEntities.get(0);
        }
        return companyUserEntity;
    }

    @Override
    public List<CompanyUserEntity> findCompanyUserByNameAndEnterpriseType(String userName, String enterpriseType) {
        return companyUserDao.findCompanyUserByNameAndEnterpriseType(userName,enterpriseType);
    }
}
