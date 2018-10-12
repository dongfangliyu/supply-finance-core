package cn.fintecher.supply.finance.loan.manager.core.audit.service;

import cn.fintecher.supply.finance.loan.manager.common.form.AuditRegisterForm;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditRegisterEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditResultEntity;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/7/4 0004下午 9:12
 */
public interface AuditRegisterService {

    void saveAuditRegisterEntity(AuditRegisterEntity auditRegisterEntity);

    List<AuditRegisterEntity> searchListAuditRegister(AuditRegisterForm auditRegisterForm);

    AuditRegisterEntity searchAuditRegisterByPid(String pid);

    void updateAuditRegister(AuditRegisterEntity auditRegisterEntity);

    void saveAuditResultEntity(AuditResultEntity auditResultEntity);

    Long searchListAuditRegisterCount(String state);

    AuditResultEntity searchAuditResultByObjectId(String objectId,String objectType);

    void updateAuditResult(AuditResultEntity auditResultEntity);

    int searchAuditRegisterListCount(AuditRegisterForm auditRegisterForm);

    AuditRegisterEntity searchAuditRegisterByRegisterId(String registerId);

    List<AuditRegisterEntity> searchAuditRegisterByName(String name);
}
