package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.AuditRegisterForm;
import cn.fintecher.supply.finance.loan.manager.common.form.AuditRegisterResultForm;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.feign.FCAuditRegisterService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.AuditRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gonghebin
 * @date 2018/7/4 0004下午 8:34
 */
@Service
public class AuditRegisterServiceImpl implements AuditRegisterService {

    @Autowired
    private FCAuditRegisterService fcAuditRegisterService;

    @Override
    public Message searchListAuditRegister(AuditRegisterForm auditRegisterForm) {
        return fcAuditRegisterService.searchListAuditRegister(auditRegisterForm);
    }

    @Override
    public Message searchAuditRegisterCompanyInfo(String pid) {
        return fcAuditRegisterService.searchAuditRegisterCompanyInfo(pid);
    }

    @Override
    public Message submitAuditRegisterResult(AuditRegisterResultForm auditRegisterResultForm) {
        return fcAuditRegisterService.submitAuditRegisterResult(auditRegisterResultForm);
    }

    @Override
    public Message searchCompanyInfo(String pid) {
        return fcAuditRegisterService.searchCompanyInfo(pid);
    }

    @Override
    public Message submitSendLink(String pid) {
        return fcAuditRegisterService.submitSendLink(pid);
    }

    @Override
    public Message searchAuditCreditStatus() {
        return fcAuditRegisterService.searchAuditCreditStatus();
    }
}
