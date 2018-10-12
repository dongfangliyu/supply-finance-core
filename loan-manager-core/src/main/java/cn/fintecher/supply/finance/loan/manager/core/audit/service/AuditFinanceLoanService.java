package cn.fintecher.supply.finance.loan.manager.core.audit.service;

import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditOrderInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.AuditSigningListForm;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Author WuTianJuan
 * @Date Created in 18:42 2018/7/23
 */
public interface AuditFinanceLoanService {

    List<AuditOrderInfoEntity> searchSigningList(AuditSigningListForm auditSigningListForm);

    int searchSignListCount(AuditSigningListForm auditSigningListForm);
}
