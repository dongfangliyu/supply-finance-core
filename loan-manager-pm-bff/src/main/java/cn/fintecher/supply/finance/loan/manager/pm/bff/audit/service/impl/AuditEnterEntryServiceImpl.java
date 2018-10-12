package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditEntryForm;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditRemindForm;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.feign.FCAuditEnterEntryService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.AuditEnterEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gonghebin
 * @date 2018/7/24 0024下午 4:01
 */
@Service
public class AuditEnterEntryServiceImpl implements AuditEnterEntryService{
    
    @Autowired
    private FCAuditEnterEntryService fcAuditEnterEntryService;

    @Override
    public Message searchEntryList(AuditRemindForm auditRemindForm) {
        return fcAuditEnterEntryService.searchEntryList(auditRemindForm);
    }

    @Override
    public Message searchEntryDetail(String pid) {
        return fcAuditEnterEntryService.searchEntryDetail(pid);
    }

    @Override
    public Message submitEntry(AuditEntryForm auditEntryForm) {
        return fcAuditEnterEntryService.submitEntry(auditEntryForm);
    }
}
