package cn.fintecher.supply.finance.loan.manager.pm.bff.company.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.company.request.BlackListFrom;
import cn.fintecher.supply.finance.loan.manager.common.company.request.CustomerFrom;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyEnterpriseEntity;
import cn.fintecher.supply.finance.loan.manager.pm.bff.company.feign.FCCompanyEnterpriseService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.company.service.CompanyEnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/7/4 0004上午 10:15
 */
@Service
public class CompanyEnterpriseServiceImpl implements CompanyEnterpriseService {

    @Autowired
    private FCCompanyEnterpriseService fcCompanyEnterpriseService;

    @Override
    public Message selectCompanyEnterprise(String name) {
        return fcCompanyEnterpriseService.selectCompanyEnterprise(name);
    }

    @Override
    public CompanyEnterpriseEntity selectByName(String name) {
        return fcCompanyEnterpriseService.selectByName(name);
    }


    /***
     * 线上为前端接口代码
     * ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     * 线下为后端接口代码
     */

    @Override
    public Message selectCustomerManagementList(CustomerFrom customerFrom) {
        return fcCompanyEnterpriseService.selectCustomerManagementList(customerFrom);
    }

    @Override
    public Message selectCustomerManagementDetail(String id) {
        return fcCompanyEnterpriseService.selectCustomerManagementDetail(id);
    }


    @Override
    public Message selectCustomerBlackList(BlackListFrom blackListFrom) {
        return fcCompanyEnterpriseService.selectCustomerBlackList(blackListFrom);
    }


    @Override
    public Message deleteBlackList(Long id, String causationInfo) {
        return fcCompanyEnterpriseService.deleteBlackList(id, causationInfo);
    }

    @Override
    public Message submitBlackList(Long id, String causationInfo) {
        return fcCompanyEnterpriseService.submitBlackList(id, causationInfo);
    }

    @Override
    public Message<List<CompanyEnterpriseEntity>> searchListConfirmationCompany() {
        return fcCompanyEnterpriseService.searchListConfirmationCompany();
    }
}
