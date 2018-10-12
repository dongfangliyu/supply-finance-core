package cn.fintecher.supply.finance.loan.manager.core.audit.service.impl;

import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditOrderInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.AuditFinanceForm;
import cn.fintecher.supply.finance.loan.manager.core.audit.dao.AuditOrderInfoDao;
import cn.fintecher.supply.finance.loan.manager.core.audit.service.AuditFinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/7/18 0018下午 7:42
 */
@Service
public class AuditFinanceServiceImpl implements AuditFinanceService{

    @Autowired
    private AuditOrderInfoDao auditOrderInfoDao;


    @Override
    public int searchFinanceListCount(AuditFinanceForm auditFinanceForm) {
        return auditOrderInfoDao.searchFinanceListCount(auditFinanceForm);
    }

    @Override
    public List<AuditOrderInfoEntity> searchFinanceList(AuditFinanceForm auditFinanceForm) {
        return auditOrderInfoDao.searchFinanceList(auditFinanceForm);
    }

    @Override
    public int searchLoanListCount(AuditFinanceForm auditFinanceForm) {
        return auditOrderInfoDao.searchLoanListCount(auditFinanceForm);
    }

    @Override
    public List<AuditOrderInfoEntity> searchLoanList(AuditFinanceForm auditFinanceForm) {
        return auditOrderInfoDao.searchLoanList(auditFinanceForm);
    }
}
