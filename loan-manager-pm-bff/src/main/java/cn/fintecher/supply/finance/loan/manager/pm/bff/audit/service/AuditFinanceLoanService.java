package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.AuditSigningListForm;

/**
 * @Author WuTianJuan
 * @Date Created in 18:32 2018/7/23
 */
public interface AuditFinanceLoanService {
    Message searchSigningList(AuditSigningListForm auditSigningListForm);

    Message searchSigningDetail(String id);
}
