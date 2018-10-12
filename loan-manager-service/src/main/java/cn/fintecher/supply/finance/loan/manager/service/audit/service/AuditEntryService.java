package cn.fintecher.supply.finance.loan.manager.service.audit.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditEntryForm;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditRemindForm;

/**
 * @author gonghebin
 * @date 2018/7/23 0023下午 5:45
 */
public interface AuditEntryService {

    Message searchEntryList(AuditRemindForm auditRemindForm);

    Message searchEntryDetail(String pid);

    Message searchEntry(AuditEntryForm auditEntryForm);

    Message submitEntry(AuditEntryForm auditEntryForm);
}
