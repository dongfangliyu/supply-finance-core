package cn.fintecher.supply.finance.loan.manager.core.audit.service;

import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditOrderInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.AuditFinanceForm;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/7/18 0018下午 7:42
 */
public interface AuditFinanceService {
    int searchFinanceListCount(AuditFinanceForm auditFinanceForm);

    List<AuditOrderInfoEntity> searchFinanceList(AuditFinanceForm auditFinanceForm);

    int searchLoanListCount(AuditFinanceForm auditFinanceForm);

    List<AuditOrderInfoEntity> searchLoanList(AuditFinanceForm auditFinanceForm);
}
