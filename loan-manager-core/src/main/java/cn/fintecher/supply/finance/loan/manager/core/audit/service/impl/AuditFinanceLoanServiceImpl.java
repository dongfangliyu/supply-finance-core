package cn.fintecher.supply.finance.loan.manager.core.audit.service.impl;

import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditOrderInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.AuditSigningListForm;
import cn.fintecher.supply.finance.loan.manager.core.audit.dao.AuditOrderInfoDao;
import cn.fintecher.supply.finance.loan.manager.core.audit.service.AuditFinanceLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author WuTianJuan
 * @Date Created in 13:56 2018/7/24
 */
@Service
public class AuditFinanceLoanServiceImpl implements AuditFinanceLoanService {
    @Autowired
    private AuditOrderInfoDao auditOrderInfoDao;

    @Override
    public List<AuditOrderInfoEntity> searchSigningList(AuditSigningListForm auditSigningListForm) {
        return auditOrderInfoDao.searchSignList(auditSigningListForm);
    }

    @Override
    public int searchSignListCount(AuditSigningListForm auditSigningListForm) {
        return auditOrderInfoDao.searchSignListCount(auditSigningListForm);
    }
}
