package cn.fintecher.supply.finance.loan.manager.pm.bff.company.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.company.request.BlackListFrom;
import cn.fintecher.supply.finance.loan.manager.common.company.request.CustomerFrom;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyEnterpriseEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyEnterpriseInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyEnterpriseEntity;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/7/4 0004上午 10:14
 */
public interface CompanyEnterpriseService {
    Message selectCompanyEnterprise(String name);

    CompanyEnterpriseEntity selectByName(String name);
    /***
     * 线上为前端接口代码
     * ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     * 线下为后端接口代码
     */

    Message selectCustomerManagementList(CustomerFrom customerFrom);

    Message selectCustomerManagementDetail(String id);

    Message selectCustomerBlackList(BlackListFrom blackListFrom);

    Message deleteBlackList(Long id, String causationInfo);

    Message submitBlackList(Long id, String causationInfo);

    Message<List<CompanyEnterpriseEntity>> searchListConfirmationCompany();
}
