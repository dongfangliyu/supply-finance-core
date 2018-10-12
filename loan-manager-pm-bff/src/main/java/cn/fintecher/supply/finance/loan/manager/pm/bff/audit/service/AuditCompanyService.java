package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.*;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.AuditCreditListForm;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.AuditCreditReviewForm;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.UpdateAuditResultForm;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditCompanyEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditCreditInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.RegisterFileEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author WuTianJuan
 * @Date Created in 17:37 2018/7/5
 */
public interface AuditCompanyService {
     Message searchAuditCompanySurvey(AuditCompanyForm auditCompanyForm);

     Message searchAuditCompanyInfo(Long id);

     Message submitExaminationStatus(AuditExaminationForm examine);

     Message submitExaminationInfo(AuditExaminationInfoForm auditExaminationInfoForm);

     Message submitSurveyInfo(AuditSurveyInfoForm auditSurveyInfoForm);

     Message submitWebsiteInfo(AuditWebsiteInfoForm auditWebsiteInfoForm);

     Message submitCredit(AuditSubmitCreditForm auditSubmitCreditForm);

     Message searchSurveyStatus(Long id);

     Message searchCreditEntry(AuditCompanyEntryForm auditCompanyEntryForm);

     /**
      * 查询财务报表期间
      */
     Message searchAccountingStatementTime();

     /**
      * 查询财务报表信息
      */
     List<CompanyFileEntity> searchAccountingStatementInfo(Long pid);

     Message searchAuditCompany(String id);

     AuditCompanyEntity searchAuditCompanyIn(Long id);

     List<RegisterFileEntity>  searchRegisterFile(Long pid);

     Message searchSurveyInfo(String id);

     Message searchWebsiteInfo(String id);

     Message submitCreditReview(AuditCreditReviewForm auditCreditReviewForm);

     AuditCreditInfoEntity searchCreditInfo(Long id);

     Message searchCreditList(AuditCreditListForm auditCreditListForm);

     Message submitCreditReviewResult(AuditCreditReviewForm auditCreditReviewForm);

     Message updateAllResult(AuditExaminationForm form);

     Message searchCreditReview(String id);
}
