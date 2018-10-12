package cn.fintecher.supply.finance.loan.manager.pm.bff.credit.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.CompanyCreditStepForm;
import cn.fintecher.supply.finance.loan.manager.common.form.SearchCompanyCreditForm;
import cn.fintecher.supply.finance.loan.manager.pm.bff.credit.feign.FCEnterpriseCreditService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.credit.service.EnterpriseCreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

/**
 * @Author WuTianJuan
 * @Date Created in 16:10 2018/6/19
 */
@Service
public class EnterpriseCreditServiceImpl implements EnterpriseCreditService {

    @Autowired
    private FCEnterpriseCreditService fcEnterpriseCreditService;

    public Message searchCompanyCreditStatus(SearchCompanyCreditForm searchCompanyCreditForm) {
       return fcEnterpriseCreditService.searchCompanyCreditStatus(searchCompanyCreditForm);
    }

    public Message startCompanyCredit(Long pid) {
        return fcEnterpriseCreditService.startCompanyCredit(pid);
    }


    public Message searchAccountingStatementTime() {
        return fcEnterpriseCreditService.searchAccountingStatementTime();
    }

    public Message applyCompanyCredit(Long pid) {
            return fcEnterpriseCreditService.applyCompanyCredit(pid);
    }

    public Message searchCompanyCreditResult(Long pid) {
        return fcEnterpriseCreditService.searchCompanyCreditResult(pid);
    }

    @Override
    public Message searchSupplierList(String pid) {
        return fcEnterpriseCreditService.searchSupplierList(pid);
    }

}


