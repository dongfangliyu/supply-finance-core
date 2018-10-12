package cn.fintecher.supply.finance.loan.manager.service.audit.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.AuditRegisterForm;
import cn.fintecher.supply.finance.loan.manager.common.form.AuditRegisterResultForm;

/**
 * @author gonghebin
 * @date 2018/7/4 0004下午 9:00
 */
public interface AuditRegisterService {
    Message searchListAuditRegister(AuditRegisterForm auditRegisterForm);

    Message searchAuditRegisterCompanyInfo(String pid);

    Message submitAuditRegisterResult(AuditRegisterResultForm auditRegisterResultForm);

    Message searchCompanyInfo(String pid);

    Message submitSendLink(String pid);

    Message searchAuditCreditStatus();
}
