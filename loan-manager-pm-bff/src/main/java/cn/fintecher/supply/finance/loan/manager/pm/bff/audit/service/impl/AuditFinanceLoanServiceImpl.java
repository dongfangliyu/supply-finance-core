package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.AuditSigningListForm;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.feign.FCAuditFinanceLoanService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.AuditFinanceLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author WuTianJuan
 * @Date Created in 18:32 2018/7/23
 */
@Service
public class AuditFinanceLoanServiceImpl implements AuditFinanceLoanService {
    @Autowired
    private FCAuditFinanceLoanService fcAuditFinanceLoanService;

    @Override
    public Message searchSigningList(AuditSigningListForm auditSigningListForm) {
        return fcAuditFinanceLoanService.searchSigningList(auditSigningListForm);
    }

    @Override
    public Message searchSigningDetail(String id) {
        return fcAuditFinanceLoanService.searchSigningDetail(id);
    }
}
