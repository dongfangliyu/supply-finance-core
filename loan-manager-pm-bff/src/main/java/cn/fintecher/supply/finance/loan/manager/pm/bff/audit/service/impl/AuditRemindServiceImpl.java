package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditRemindForm;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditTaskRemindForm;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.feign.FCAuditRemindService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.AuditRemindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gonghebin
 * @date 2018/7/23 0023上午 10:31
 */
@Service
public class AuditRemindServiceImpl implements AuditRemindService {

    @Autowired
    private FCAuditRemindService fcAuditRemindService;

    @Override
    public Message searchRemindList(AuditRemindForm auditRemindForm) {
        return fcAuditRemindService.searchRemindList(auditRemindForm);
    }

    @Override
    public Message searchRemindDetail(String pid) {
        return fcAuditRemindService.searchRemindDetail(pid);
    }

    @Override
    public Message searchRemind(AuditTaskRemindForm auditTaskRemindForm) {
        return fcAuditRemindService.searchRemind(auditTaskRemindForm);
    }

    @Override
    public Message submitRemind(AuditTaskRemindForm auditTaskRemindForm) {
        return fcAuditRemindService.submitRemind(auditTaskRemindForm);
    }

    @Override
    public Message deleteRemind(String pid) {
        return fcAuditRemindService.deleteRemind(pid);
    }
}
