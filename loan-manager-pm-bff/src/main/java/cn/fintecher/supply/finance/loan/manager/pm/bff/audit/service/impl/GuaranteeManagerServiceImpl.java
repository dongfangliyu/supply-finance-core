package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.guarantee.AuditFrontGuaranteeListForm;
import cn.fintecher.supply.finance.loan.manager.common.form.guarantee.AuditGuaranteeListForm;
import cn.fintecher.supply.finance.loan.manager.common.form.guarantee.AuditManagerListForm;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditCompanyEntity;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.feign.FCGuaranteeManagerService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.GuaranteeManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author WuTianJuan
 * @Date Created in 20:11 2018/7/17
 */

@Service
public class GuaranteeManagerServiceImpl implements GuaranteeManagerService {

    @Autowired
    private FCGuaranteeManagerService fcGuaranteeManagerService;

    @Override
    public Message searchGuaranteeList(AuditGuaranteeListForm auditGuaranteeListForm) {
        return fcGuaranteeManagerService.searchGuaranteeList(auditGuaranteeListForm);
    }

    @Override
    public Message submitResult(AuditManagerListForm auditManagerListForm) {
        return fcGuaranteeManagerService.submitResult(auditManagerListForm);
    }

    @Override
    public Message searchFrontDetail(String pid) {
        return fcGuaranteeManagerService.searchFrontDetail(pid);
    }

    @Override
    public Message submitFrontResult(AuditManagerListForm auditManagerListForm) {
        return fcGuaranteeManagerService.submitFrontResult(auditManagerListForm);
    }

    @Override
    public Message searchAuditCompanyInfo(String id) {
        return fcGuaranteeManagerService.searchAuditCompanyInfo(id);
    }

    @Override
    public AuditCompanyEntity searchAuditCompanyId(String id) {
        return fcGuaranteeManagerService.searchAuditCompanyId(id);
    }

    @Override
    public Message searchDetail(Long pid) {
        return fcGuaranteeManagerService.searchDetail(pid);
    }


    @Override
    public Message searchFrontGuaranteeList(AuditFrontGuaranteeListForm auditFrontGuaranteeListForm) {
        return fcGuaranteeManagerService.searchFrontGuaranteeList(auditFrontGuaranteeListForm);
    }
}
