package cn.fintecher.supply.finance.loan.manager.service.audit.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditRemindForm;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditTaskRemindForm;

/**
 * @author gonghebin
 * @date 2018/7/23 0023上午 10:36
 */
public interface AuditRemindService {
    Message searchRemindList(AuditRemindForm auditRemindForm);

    Message searchRemindDetail(String pid);

    Message searchRemind(AuditTaskRemindForm auditTaskRemindForm);

    Message submitRemind(AuditTaskRemindForm auditTaskRemindForm);

    Message deleteRemind(String pid);
}
