package cn.fintecher.supply.finance.loan.manager.service.audit.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.AuditFinanceForm;

/**
 * @author gonghebin
 * @date 2018/7/19 0019上午 10:55
 */
public interface AuditLoanService {
    Message searchLoanList(AuditFinanceForm auditFinanceForm);

    Message searchLoanDetail(String pid);

    Message submitLoanPass(String pid,String loanTime, String userName);

    Message submitLoanFail(String pid, String userName);
}
