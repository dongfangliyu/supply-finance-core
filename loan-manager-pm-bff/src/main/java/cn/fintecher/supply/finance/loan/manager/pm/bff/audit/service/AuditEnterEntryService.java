package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditEntryForm;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditRemindForm;

/**
 * @author gonghebin
 * @date 2018/7/24 0024下午 3:57
 */
public interface AuditEnterEntryService {
    Message searchEntryList(AuditRemindForm auditRemindForm);

    Message searchEntryDetail(String pid);

    Message submitEntry(AuditEntryForm auditEntryForm);
}
