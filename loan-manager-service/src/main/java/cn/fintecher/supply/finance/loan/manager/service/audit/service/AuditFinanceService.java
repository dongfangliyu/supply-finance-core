package cn.fintecher.supply.finance.loan.manager.service.audit.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.AuditFinanceForm;

/**
 * @author gonghebin
 * @date 2018/7/18 0018下午 3:30
 */
public interface AuditFinanceService {

    Message searchFinanceList(AuditFinanceForm auditFinanceForm);

    Message searchFinanceDetail(String pid);

    Message submitFinancePass(String pid,String userName);

    Message submitFinanceFail(String pid,String userName);
}
