package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.AuditFinanceForm;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.feign.FCAuditFinanceService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.AuditFinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gonghebin
 * @date 2018/7/18 0018下午 2:31
 */
@Service
public class AuditFinanceServiceImpl implements AuditFinanceService{

    @Autowired
    private FCAuditFinanceService fcAuditFinanceService;

    @Override
    public Message searchFinanceList(AuditFinanceForm auditFinanceForm) {
        return fcAuditFinanceService.searchFinanceList(auditFinanceForm);
    }

    @Override
    public Message searchFinanceDetail(String pid) {
        return fcAuditFinanceService.searchFinanceDetail(pid);
    }

    @Override
    public Message submitFinancePass(String pid,String userName) {
        return fcAuditFinanceService.submitFinancePass(pid,userName);
    }

    @Override
    public Message submitFinanceFail(String pid,String userName) {
        return fcAuditFinanceService.submitFinanceFail(pid,userName);
    }
}
