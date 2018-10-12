package cn.fintecher.supply.finance.loan.manager.core.audit.service.impl;

import cn.fintecher.supply.finance.loan.manager.common.form.AuditRegisterForm;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditRegisterEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditResultEntity;
import cn.fintecher.supply.finance.loan.manager.core.audit.dao.AuditRegisterDao;
import cn.fintecher.supply.finance.loan.manager.core.audit.dao.AuditResultDao;
import cn.fintecher.supply.finance.loan.manager.core.audit.service.AuditRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/7/4 0004下午 9:14
 */
@Service
public class AuditRegisterServiceImpl implements AuditRegisterService {

    @Autowired
    private AuditRegisterDao auditRegisterDao;

    @Autowired
    private AuditResultDao auditResultDao;

    @Override
    public void saveAuditRegisterEntity(AuditRegisterEntity auditRegisterEntity) {
        auditRegisterDao.saveAuditRegisterEntity(auditRegisterEntity);
    }

    @Override
    public List<AuditRegisterEntity> searchListAuditRegister(AuditRegisterForm auditRegisterForm) {
        return auditRegisterDao.searchListAuditRegister(auditRegisterForm);
    }

    @Override
    public AuditRegisterEntity searchAuditRegisterByPid(String pid) {
        return auditRegisterDao.searchAuditRegisterByPid(pid);
    }

    @Override
    public void updateAuditRegister(AuditRegisterEntity auditRegisterEntity) {
        auditRegisterDao.updateAuditRegister(auditRegisterEntity);
    }

    @Override
    public void saveAuditResultEntity(AuditResultEntity auditResultEntity) {
        auditResultDao.saveAuditResultEntity(auditResultEntity);
    }

    @Override
    public Long searchListAuditRegisterCount(String state) {
        return auditRegisterDao.searchListAuditRegisterCount(state);
    }

    @Override
    public AuditResultEntity searchAuditResultByObjectId(String objectId,String objectType) {
        return auditResultDao.searchAuditResultByObjectId(objectId,objectType);
    }

    @Override
    public void updateAuditResult(AuditResultEntity auditResultEntity) {
        auditResultDao.updateAuditResult(auditResultEntity);
    }

    @Override
    public int searchAuditRegisterListCount(AuditRegisterForm auditRegisterForm) {
        return auditRegisterDao.searchAuditRegisterListCount(auditRegisterForm);
    }

    @Override
    public AuditRegisterEntity searchAuditRegisterByRegisterId(String registerId) {
        return auditRegisterDao.searchAuditRegisterByRegisterId(registerId);
    }

    @Override
    public List<AuditRegisterEntity> searchAuditRegisterByName(String name) {
        return auditRegisterDao.searchAuditRegisterByName(name);
    }
}
