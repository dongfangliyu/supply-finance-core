package cn.fintecher.supply.finance.loan.manager.core.audit.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.*;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.AuditCreditListForm;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.AuditCreditReviewForm;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.AuditResultForm;
import cn.fintecher.supply.finance.loan.manager.common.model.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author WuTianJuan
 * @Date Created in 18:12 2018/7/5
 */
public interface AuditCompanyService {
    List<AuditCompanyEntity> searchAuditCompanySurvey(AuditCompanyForm auditCompanyForm);

    AuditCompanyEntity searchAuditCompanyInfo(Long id);

    Boolean submitExaminationStatus(AuditCompanyEntity entity);

    Boolean submitSurveyInfo(AuditSurveyInfoEntity entity);

    Boolean submitWebsiteInfo(AuditWebsiteInfoEntity entity);

    Boolean submitCredit(AuditCompanyEntity entity);

    int searchAuditCompanyListCount(AuditCompanyForm auditCompanyForm);

    Boolean updateAuditCompanyEntity(AuditCompanyEntity entity);

    String  searchSurveyStatus(Long id);

    AuditResultEntity searchAuditResult(AuditResultEntity auditResultEntity);

    List<AuditResultEntity> searchResultList(AuditSearchResultListForm auditSearchResultListForm);

    List<AuditCompanyEntity> searchCreditEntry(AuditCompanyEntryForm auditCompanyEntryForm);

    int searchCreditEntryCount(AuditCompanyEntryForm auditCompanyEntryForm);

    List<AuditResultEntity> searchAllResultList(String objectId);

    AuditSurveyInfoEntity searchSurveyInfo(String id);

    AuditResultEntity searchSurveyOpinion(AuditResultForm form);

    AuditWebsiteInfoEntity searchWebsiteInfo(String id);

    Boolean submitCreditReview(AuditCreditInfoEntity entity);

    List<AuditCreditInfoEntity> searchCreditInfo(Long id);

    List<AuditCompanyEntity> searchCreditList(AuditCreditListForm auditCreditListForm);

    int searchCreditListCount(AuditCreditListForm auditCreditListForm);

    AuditCompanyEntity searchCompanyById(Long pid);

    AuditResultEntity searchResultByObjId(String pid,String type);

    Boolean updateCreditReview(AuditCreditInfoEntity entity);

    AuditCreditInfoEntity getEntityByEnterpriseId(Long pid);

    int searchAuditCreditCount(String auditStatus);

    int searchCreditInfoCount(String pid);

}