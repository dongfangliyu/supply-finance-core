package cn.fintecher.supply.finance.loan.manager.core.audit.dao;

import cn.fintecher.supply.finance.loan.manager.common.form.AuditSearchResultListForm;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.AuditResultForm;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditResultEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/7/4 0004下午 9:31
 */
@Mapper
public interface AuditResultDao {

    void saveAuditResultEntity(AuditResultEntity auditResultEntity);

    AuditResultEntity searchAuditResultByObjectId(@Param(value="objectId") String objectId,@Param(value="objectType") String objectType);

    void updateAuditResult(AuditResultEntity auditResultEntity);

    AuditResultEntity searchAuditResult(AuditResultEntity auditResultEntity);

    List<AuditResultEntity> searchResultList(AuditSearchResultListForm auditSearchResultListForm);

    List<AuditResultEntity> searchAllResultList(String objectId);

    AuditResultEntity searchSurveyOpinion(AuditResultForm form);

    AuditResultEntity searchResultByObjId(@Param(value="pid") String pid, @Param(value="type") String type);
}
