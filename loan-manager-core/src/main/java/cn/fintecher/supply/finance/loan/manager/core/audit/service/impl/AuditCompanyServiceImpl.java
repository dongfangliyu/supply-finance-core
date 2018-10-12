package cn.fintecher.supply.finance.loan.manager.core.audit.service.impl;

import cn.fintecher.supply.finance.loan.manager.common.form.AuditCompanyEntryForm;
import cn.fintecher.supply.finance.loan.manager.common.form.AuditCompanyForm;
import cn.fintecher.supply.finance.loan.manager.common.form.AuditCreditEntryForm;
import cn.fintecher.supply.finance.loan.manager.common.form.AuditSearchResultListForm;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.AuditCreditListForm;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.AuditCreditReviewForm;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.AuditResultForm;
import cn.fintecher.supply.finance.loan.manager.common.model.*;
import cn.fintecher.supply.finance.loan.manager.core.audit.dao.*;
import cn.fintecher.supply.finance.loan.manager.core.audit.service.AuditCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author WuTianJuan
 * @Date Created in 18:12 2018/7/5
 */
@Service
public class AuditCompanyServiceImpl implements AuditCompanyService {

    @Autowired
    private AuditCompanyDao auditCompanyDao;

    @Autowired
    private AuditResultDao auditResultDao;

    @Autowired
    private AuditSurveyinfoDao auditSurveyinfoDao;

    @Autowired
    private AuditWebsiteInfoDao auditWebsiteInfoDao;

    @Autowired
    private AuditCreditInfoDao auditCreditInfoDao;

    @Override
    public List<AuditCompanyEntity> searchAuditCompanySurvey(AuditCompanyForm auditCompanyForm) {
        return auditCompanyDao.searchAuditCompanySurvey(auditCompanyForm);
    }

    @Override
    public AuditCompanyEntity searchAuditCompanyInfo(Long id) {
        return auditCompanyDao.searchAuditCompanyInfo(id);
    }

    @Override
    public Boolean submitExaminationStatus(AuditCompanyEntity entity) {
        return auditCompanyDao.submitExaminationStatus(entity);
    }

    @Override
    public Boolean submitSurveyInfo(AuditSurveyInfoEntity entity) {
        return auditSurveyinfoDao.submitSurveyInfo(entity);
    }

    @Override
    public Boolean submitWebsiteInfo(AuditWebsiteInfoEntity entity) {
        return auditWebsiteInfoDao.submitWebsiteInfo(entity);
    }

    @Override
    public Boolean submitCredit(AuditCompanyEntity entity) {
        return auditCompanyDao.submitCredit(entity);
    }

    @Override
    public int searchAuditCompanyListCount(AuditCompanyForm auditCompanyForm) {
        return auditCompanyDao.searchAuditCompanyListCount(auditCompanyForm);
    }

    @Override
    public Boolean updateAuditCompanyEntity(AuditCompanyEntity entity) {
        return auditCompanyDao.updateAuditCompanyEntity(entity);
    }

    @Override
    public String searchSurveyStatus(Long id) {
        return auditCompanyDao.searchSurveyStatus(id);
    }

    @Override
    public AuditResultEntity searchAuditResult(AuditResultEntity auditResultEntity) {
        return auditResultDao.searchAuditResult(auditResultEntity);
    }

    @Override
    public List<AuditResultEntity> searchResultList(AuditSearchResultListForm auditSearchResultListForm) {
        return auditResultDao.searchResultList(auditSearchResultListForm);
    }

    @Override
    public List<AuditCompanyEntity> searchCreditEntry(AuditCompanyEntryForm auditCompanyEntryForm) {
        return auditCompanyDao.searchCreditEntry(auditCompanyEntryForm);
    }

    @Override
    public int searchCreditEntryCount(AuditCompanyEntryForm auditCompanyEntryForm) {
        return auditCompanyDao.searchCreditEntryCount(auditCompanyEntryForm);
    }

    @Override
    public List<AuditResultEntity> searchAllResultList(String objectId) {
        return auditResultDao.searchAllResultList(objectId);
    }

    @Override
    public AuditSurveyInfoEntity searchSurveyInfo(String id) {
        return auditSurveyinfoDao.searchSurveyInfo(id);
    }

    @Override
    public AuditResultEntity searchSurveyOpinion(AuditResultForm form) {
        return auditResultDao.searchSurveyOpinion(form);
    }

    @Override
    public AuditWebsiteInfoEntity searchWebsiteInfo(String id) {
        return auditWebsiteInfoDao.searchWebsiteInfo(id);
    }

    @Override
    public Boolean submitCreditReview(AuditCreditInfoEntity entity) {
        return auditCreditInfoDao.submitCreditReview(entity);
    }

    @Override
    public List<AuditCreditInfoEntity> searchCreditInfo(Long id) {
        return auditCreditInfoDao.searchCreditInfo(id);
    }

    @Override
    public List<AuditCompanyEntity> searchCreditList(AuditCreditListForm auditCreditListForm) {
        return auditCompanyDao.searchCreditList(auditCreditListForm);
    }

    @Override
    public int searchCreditListCount(AuditCreditListForm auditCreditListForm) {
        return auditCompanyDao.searchCreditListCount(auditCreditListForm);
    }

    @Override
    public AuditCompanyEntity searchCompanyById(Long pid) {
        return auditCompanyDao.searchCompanyById(pid);
    }

    @Override
    public AuditResultEntity searchResultByObjId(String pid, String type) {
        return auditResultDao.searchResultByObjId(pid,type);
    }

    @Override
    public Boolean updateCreditReview(AuditCreditInfoEntity entity) {
        return auditCreditInfoDao.updateCreditReview(entity);
    }

	@Override
	public AuditCreditInfoEntity getEntityByEnterpriseId(Long pid) {
		// TODO Auto-generated method stub
		return auditCreditInfoDao.getEntityByEnterpriseId(pid);
	}

    @Override
    public int searchAuditCreditCount(String auditStatus) {
        return auditCompanyDao.searchAuditCreditCount(auditStatus);
    }

    @Override
    public int searchCreditInfoCount(String pid) {
        return auditCreditInfoDao.searchCreditInfoCount(pid);
    }
}
