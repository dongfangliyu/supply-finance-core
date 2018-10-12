package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.*;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.AuditCreditListForm;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.AuditCreditReviewForm;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.UpdateAuditResultForm;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditCompanyEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditCreditInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.RegisterFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.response.AuditCompanyInfoResponse;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.feign.FCAuditCompanyService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.AuditCompanyService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.credit.feign.FCEnterpriseCreditService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.credit.feign.FCEnterpriseFinancialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author WuTianJuan
 * @Date Created in 17:37 2018/7/5
 */
@Service
public class AuditCompanyServiceImpl implements AuditCompanyService {
    @Autowired
    private FCAuditCompanyService fcAuditCompanyService;

    @Autowired
    private FCEnterpriseCreditService fcEnterpriseCreditService;

    @Autowired
    private FCEnterpriseFinancialService enterpriseFinancialService;


    @Override
    public Message searchAuditCompanySurvey(AuditCompanyForm auditCompanyForm) {
        return fcAuditCompanyService.searchAuditCompanySurvey(auditCompanyForm);
    }

    @Override
    public Message searchAuditCompanyInfo(Long id) {
        Message msg = new Message();
        AuditCompanyInfoResponse response = new  AuditCompanyInfoResponse();
        AuditCompanyEntity entity = fcAuditCompanyService.searchAuditCompanyInfo(id);
        if(entity.getFinanceStatus()=="1" && entity.getMaterialStatus()=="1"){
            response.setFlag(true);
        }
        response.setEntity(entity);
        msg.setMessage(response);
        msg.setCode(0);
        return msg;
    }

    @Override
    public Message submitExaminationStatus(AuditExaminationForm examine) {
        return fcAuditCompanyService.submitExaminationStatus(examine);
    }

    @Override
    public Message submitExaminationInfo(AuditExaminationInfoForm auditExaminationInfoForm) {
        return fcAuditCompanyService.submitExaminationInfo(auditExaminationInfoForm);
    }

    @Override
    public Message submitSurveyInfo(AuditSurveyInfoForm auditSurveyInfoForm) {
        return fcAuditCompanyService.submitSurveyInfo(auditSurveyInfoForm);
    }

    @Override
    public Message submitWebsiteInfo(AuditWebsiteInfoForm auditWebsiteInfoForm) {
        return fcAuditCompanyService.submitWebsiteInfo(auditWebsiteInfoForm);
    }

    @Override
    public Message submitCredit(AuditSubmitCreditForm auditSubmitCreditForm) {
        return fcAuditCompanyService.submitCredit(auditSubmitCreditForm);
    }

    @Override
    public Message searchSurveyStatus(Long id) {
        return fcAuditCompanyService.searchSurveyStatus(id);
    }

    @Override
    public Message searchCreditEntry(AuditCompanyEntryForm auditCompanyEntryForm) {
        return fcAuditCompanyService.searchCreditEntry(auditCompanyEntryForm);
    }

    @Override
    public Message searchAccountingStatementTime() {
        return fcEnterpriseCreditService.searchAccountingStatementTime();
    }


    public List<CompanyFileEntity> searchAccountingStatementInfo(Long pid) {
        return enterpriseFinancialService.searchAccountingStatementInfo(pid);
    }

    @Override
    public Message searchAuditCompany(String id) {
        return fcAuditCompanyService.searchAuditCompany(id);
    }

    @Override
    public AuditCompanyEntity searchAuditCompanyIn(Long id) {
        return fcAuditCompanyService.searchAuditCompanyIn(id);
    }

    @Override
    public List<RegisterFileEntity> searchRegisterFile(Long pid) {
        return fcAuditCompanyService.searchRegisterFile(pid);
    }

    @Override
    public Message searchSurveyInfo(String id) {
        return fcAuditCompanyService.searchSurveyInfo(id);
    }

    @Override
    public Message searchWebsiteInfo(String id) {
        return fcAuditCompanyService.searchWebsiteInfo(id);
    }

    @Override
    public Message submitCreditReview(AuditCreditReviewForm auditCreditReviewForm) {
        return fcAuditCompanyService.submitCreditReview(auditCreditReviewForm);
    }

    @Override
    public AuditCreditInfoEntity searchCreditInfo(Long id) {
        return fcAuditCompanyService.searchCreditInfo(id);
    }

    @Override
    public Message searchCreditList(AuditCreditListForm auditCreditListForm) {
        return fcAuditCompanyService.searchCreditList(auditCreditListForm);
    }

    @Override
    public Message submitCreditReviewResult(AuditCreditReviewForm auditCreditReviewForm) {
        return fcAuditCompanyService.submitCreditReviewResult(auditCreditReviewForm);
    }

    @Override
    public Message updateAllResult(AuditExaminationForm form) {
        return fcAuditCompanyService.updateAllResult(form);
    }

    @Override
    public Message searchCreditReview(String id) {
        return fcAuditCompanyService.searchCreditReview(id);
    }

}
