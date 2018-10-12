package cn.fintecher.supply.finance.loan.manager.core.audit.dao;

import cn.fintecher.supply.finance.loan.manager.common.form.AuditRegisterForm;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditRegisterEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/7/4 0004下午 9:22
 */
@Mapper
public interface AuditRegisterDao {

    void saveAuditRegisterEntity(AuditRegisterEntity auditRegisterEntity);

    List<AuditRegisterEntity> searchListAuditRegister(AuditRegisterForm auditRegisterForm);

    AuditRegisterEntity searchAuditRegisterByPid(String pid);

    void updateAuditRegister(AuditRegisterEntity auditRegisterEntity);

    Long searchListAuditRegisterCount(String state);

    int searchAuditRegisterListCount(AuditRegisterForm auditRegisterForm);

    AuditRegisterEntity searchAuditRegisterByRegisterId(String registerId);

    List<AuditRegisterEntity> searchAuditRegisterByName(String name);
}
