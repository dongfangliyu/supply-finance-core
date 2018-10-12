package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditEntryForm;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditRemindForm;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.feign.FCAuditEntryService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.AuditEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gonghebin
 * @date 2018/7/23 0023下午 5:42
 */
@Service
public class AuditEntryServiceImpl implements AuditEntryService {

    @Autowired
    private FCAuditEntryService fcAuditEntryService;

    @Override
    public Message searchEntryList(AuditRemindForm auditRemindForm) {
        return fcAuditEntryService.searchEntryList(auditRemindForm);
    }

    @Override
    public Message searchEntryDetail(String pid) {
        return fcAuditEntryService.searchEntryDetail(pid);
    }

    @Override
    public Message searchEntry(AuditEntryForm auditEntryForm) {
        return fcAuditEntryService.searchEntry(auditEntryForm);
    }

    @Override
    public Message submitEntry(AuditEntryForm auditEntryForm) {
        return fcAuditEntryService.submitEntry(auditEntryForm);
    }
}
