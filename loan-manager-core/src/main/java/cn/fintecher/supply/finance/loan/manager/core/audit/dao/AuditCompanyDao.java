package cn.fintecher.supply.finance.loan.manager.core.audit.dao;

import cn.fintecher.supply.finance.loan.manager.common.form.AuditCompanyEntryForm;
import cn.fintecher.supply.finance.loan.manager.common.form.AuditCompanyForm;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.AuditCreditListForm;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditCompanyEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditWebsiteInfoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/7/4 0004下午 9:30
 */
@Mapper
public interface AuditCompanyDao {
    List<AuditCompanyEntity> searchAuditCompanySurvey(AuditCompanyForm auditCompanyForm);

    AuditCompanyEntity searchAuditCompanyInfo(Long id);

    Boolean submitExaminationStatus(AuditCompanyEntity entity);

    Boolean submitCredit(AuditCompanyEntity entity);

    int searchAuditCompanyListCount(AuditCompanyForm auditCompanyForm);

    Boolean updateAuditCompanyEntity(AuditCompanyEntity entity);

    String searchSurveyStatus(Long id);

    List<AuditCompanyEntity> searchCreditEntry(AuditCompanyEntryForm auditCompanyEntryForm);

    int searchCreditEntryCount(AuditCompanyEntryForm auditCompanyEntryForm);

    List<AuditCompanyEntity> searchCreditList(AuditCreditListForm auditCreditListForm);

    int searchCreditListCount(AuditCreditListForm auditCreditListForm);

    AuditCompanyEntity searchCompanyById(Long pid);

    AuditCompanyEntity searchAuditCompanyId(String id);

    int searchAuditCreditCount(String auditStatus);
}
