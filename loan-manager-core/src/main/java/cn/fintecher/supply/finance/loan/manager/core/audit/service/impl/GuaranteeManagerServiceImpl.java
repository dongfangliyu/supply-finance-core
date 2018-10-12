package cn.fintecher.supply.finance.loan.manager.core.audit.service.impl;

import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditOrderInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessOrderEntity;
import cn.fintecher.supply.finance.loan.manager.common.form.guarantee.AuditFrontGuaranteeListForm;
import cn.fintecher.supply.finance.loan.manager.common.form.guarantee.AuditGuaranteeListForm;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditCompanyEntity;
import cn.fintecher.supply.finance.loan.manager.core.audit.dao.AuditCompanyDao;
import cn.fintecher.supply.finance.loan.manager.core.audit.dao.AuditOrderInfoDao;
import cn.fintecher.supply.finance.loan.manager.core.audit.service.GuaranteeManagerService;
import cn.fintecher.supply.finance.loan.manager.core.business.dao.BusinessOrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author WuTianJuan
 * @Date Created in 18:02 2018/7/18
 */
@Service
public class GuaranteeManagerServiceImpl implements GuaranteeManagerService {
    @Autowired
    private AuditOrderInfoDao auditOrderInfoDao;

    @Autowired
    private AuditCompanyDao companyDao;

    @Autowired
    private BusinessOrderDao businessOrderDao;

    public List<AuditOrderInfoEntity> searchGuaranteeList(AuditGuaranteeListForm auditGuaranteeListForm){
        return auditOrderInfoDao.searchGuaranteeList(auditGuaranteeListForm);
    }

    public int searchGuaranteeListCount(AuditGuaranteeListForm auditGuaranteeListForm){
        return auditOrderInfoDao.searchGuaranteeListCount(auditGuaranteeListForm);
    }

    //      public Boolean submitResult(@RequestBody AuditManagerListForm auditManagerListForm){
//          //return auditOrderInfoDao.updateOrderInfo()
//          return null;
//      }
    public Integer updateGuaranteeInfo(@RequestBody AuditOrderInfoEntity auditOrderInfoEntity){
        return auditOrderInfoDao.updateOrderInfo(auditOrderInfoEntity);
    }
    public AuditOrderInfoEntity searchOrderInfoById(@RequestParam(value="pid") String pid){
        return auditOrderInfoDao.queryOrderInfoByPid(pid);
    }

    @Override
    public int searchFrontGuaranteeListCount(AuditFrontGuaranteeListForm auditFrontGuaranteeListForm) {
        return auditOrderInfoDao.searchFrontGuaranteeListCount(auditFrontGuaranteeListForm);
    }

    @Override
    public AuditCompanyEntity searchAuditCompanyId(String id) {
        return companyDao.searchAuditCompanyId(id);
    }

    @Override
    public BusinessOrderEntity searchBusinessOrederByOwnerId(String ownerId) {
        return businessOrderDao.searchBusinessOrederByOwnerId(ownerId);
    }

    @Override
    public List<AuditOrderInfoEntity> searchFrontGuaranteeList(AuditFrontGuaranteeListForm auditFrontGuaranteeListForm) {
        return auditOrderInfoDao.searchFrontGuaranteeList(auditFrontGuaranteeListForm);
    }

}
