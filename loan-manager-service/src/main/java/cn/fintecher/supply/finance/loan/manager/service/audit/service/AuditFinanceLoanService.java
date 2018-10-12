package cn.fintecher.supply.finance.loan.manager.service.audit.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.AuditSigningListForm;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author WuTianJuan
 * @Date Created in 18:37 2018/7/23
 */
@RestController
@RequestMapping("/audit/financeLoan")
public interface AuditFinanceLoanService {

    Message searchSigningList(AuditSigningListForm auditSigningListForm);

    Message searchSigningDetail(String id);
}
