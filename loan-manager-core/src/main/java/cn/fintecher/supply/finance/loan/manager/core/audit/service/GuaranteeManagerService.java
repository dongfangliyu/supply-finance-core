package cn.fintecher.supply.finance.loan.manager.core.audit.service;

import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditOrderInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessOrderEntity;
import cn.fintecher.supply.finance.loan.manager.common.form.guarantee.AuditFrontGuaranteeListForm;
import cn.fintecher.supply.finance.loan.manager.common.form.guarantee.AuditGuaranteeListForm;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditCompanyEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author WuTianJuan
 * @Date Created in 18:02 2018/7/18
 */
public interface GuaranteeManagerService {
    List<AuditOrderInfoEntity> searchGuaranteeList(AuditGuaranteeListForm auditGuaranteeListForm);

    int searchGuaranteeListCount(AuditGuaranteeListForm auditGuaranteeListForm);

    Integer updateGuaranteeInfo(@RequestBody AuditOrderInfoEntity auditOrderInfoEntity);

    AuditOrderInfoEntity searchOrderInfoById(@RequestParam(value="pid") String pid);

    List<AuditOrderInfoEntity> searchFrontGuaranteeList(@RequestBody AuditFrontGuaranteeListForm auditFrontGuaranteeListForm);

    int searchFrontGuaranteeListCount(@RequestBody AuditFrontGuaranteeListForm auditFrontGuaranteeListForm);

    AuditCompanyEntity searchAuditCompanyId(@RequestParam(value="id") String id);

    BusinessOrderEntity searchBusinessOrederByOwnerId(@RequestParam(value = "ownerId") String ownerId);
}
