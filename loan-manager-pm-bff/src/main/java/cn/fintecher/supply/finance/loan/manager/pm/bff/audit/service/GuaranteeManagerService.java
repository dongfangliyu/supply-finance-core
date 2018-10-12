package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.guarantee.AuditFrontGuaranteeListForm;
import cn.fintecher.supply.finance.loan.manager.common.form.guarantee.AuditGuaranteeListForm;
import cn.fintecher.supply.finance.loan.manager.common.form.guarantee.AuditManagerListForm;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditCompanyEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author WuTianJuan
 * @Date Created in 20:12 2018/7/17
 */
public interface GuaranteeManagerService {
    Message searchGuaranteeList(AuditGuaranteeListForm auditGuaranteeListForm);

    Message submitResult(AuditManagerListForm auditManagerListForm);

    Message searchFrontGuaranteeList(AuditFrontGuaranteeListForm auditFrontGuaranteeListForm);

    Message searchFrontDetail(String pid);

    Message submitFrontResult(AuditManagerListForm auditManagerListForm);

    Message searchAuditCompanyInfo(String id);

    AuditCompanyEntity searchAuditCompanyId(String id);

    Message searchDetail(Long pid);
}
