package cn.fintecher.supply.finance.loan.manager.service.audit.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.*;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.AuditCreditListForm;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.AuditCreditReviewForm;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.AuditResultForm;
import cn.fintecher.supply.finance.loan.manager.common.model.*;
import cn.fintecher.supply.finance.loan.manager.common.util.Constants;
import cn.fintecher.supply.finance.loan.manager.common.util.DateUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.PageResponse;
import cn.fintecher.supply.finance.loan.manager.service.audit.feign.FCAuditCompanyCore;
import cn.fintecher.supply.finance.loan.manager.service.audit.feign.FCAuditRegisterCore;
import cn.fintecher.supply.finance.loan.manager.service.audit.service.AuditCompanyService;
import cn.fintecher.supply.finance.loan.manager.service.company.feign.FCCompanyEnterpriseCore;
import cn.fintecher.supply.finance.loan.manager.service.credit.feign.FCEnterpriseCreditCore;
import cn.fintecher.supply.finance.loan.manager.service.pro.feign.FCProContractCore;
import cn.fintecher.supply.finance.loan.manager.service.register.feign.FCRegisterFileCore;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author WuTianJuan
 * @Date Created in 18:00 2018/7/5
 */
@Service
public class AuditCompanyServiceImpl implements AuditCompanyService {
    @Autowired
    private FCAuditCompanyCore fcAuditCompanyCore;

    @Autowired
    private FCAuditRegisterCore fcAuditRegisterCore;

    @Autowired
    private FCCompanyEnterpriseCore fcCompanyEnterpriseCore;

    @Autowired
    private FCRegisterFileCore fcRegisterFileCore;

    @Autowired
    private FCEnterpriseCreditCore fcEnterpriseCreditCore;

    @Autowired
    private AuditCompanyService auditCompanyService;

    @Autowired
    private FCProContractCore fcProContractCore;

    @Override
    public Message searchAuditCompanySurvey(AuditCompanyForm auditCompanyForm) {
        Message msg = new Message();
        PageResponse<Object> pageResponse = new PageResponse<>();
        int total = 0;
        if(auditCompanyForm.getPageNo() != 0){
            total = fcAuditCompanyCore.searchAuditCompanyListCount(auditCompanyForm);
        }
        List<AuditCompanyEntity> list = fcAuditCompanyCore.searchAuditCompanySurvey(auditCompanyForm);
        if(null!=list){
            pageResponse.setData(list);
            pageResponse.setTotal(total);
            pageResponse.setPageNo(auditCompanyForm.getPageNo());
            pageResponse.setPageSize(auditCompanyForm.getPageSize());
            msg.setMessage(pageResponse);
            msg.setCode(0);
        }else{
            msg.setCode(-1);
        }
        return msg;
    }

    @Override
    public AuditCompanyEntity searchAuditCompanyInfo(Long id) {
        return fcAuditCompanyCore.searchAuditCompanyInfo(id);
    }

    @Override
    public Message submitExaminationStatus(AuditExaminationForm examine) {
        Message msg = new Message();
        String type = examine.getType();
        AuditCompanyEntity entity = new AuditCompanyEntity();
        entity.setPid(examine.getId());
        if(type.equals("0")){
            entity.setFinanceStatus("1");//将财务信息检查设置成已检查
        }
        if(type.equals("1")){
            entity.setMaterialStatus("1");//将影像材料检查信息设置成已检查
        }
        fcAuditCompanyCore.submitExaminationStatus(entity);
        msg.setCode(0);
        msg.setType("audit-companyCore");
        return msg;
    }

    public AuditResultEntity searchResultByObjId(AuditExaminationInfoForm auditExaminationInfoForm){
        String pid = auditExaminationInfoForm.getPid()+"";
        String type = auditExaminationInfoForm.getType();
        AuditResultEntity result = fcAuditCompanyCore.searchResultByObjId(pid,type);
        return result;
    }

    @Override
    public Message submitExaminationInfo(AuditExaminationInfoForm auditExaminationInfoForm) {
        Message msg = new Message();
        Long id =auditExaminationInfoForm.getPid();
        if(""==auditExaminationInfoForm.getRemark()){
            msg.setCode(-1);
            msg.setMessage("审核意见不能为空");
            return msg;
        }
        AuditResultEntity resultEntity =  searchResultByObjId(auditExaminationInfoForm);
        if(null!=resultEntity){
            resultEntity.setStatus(Constants.DATA_STATUS_DEL);
            fcAuditRegisterCore.updateAuditResult(resultEntity);
        }
            //添加企業审核意見
        AuditResultEntity result = new AuditResultEntity();
        result.setContent(auditExaminationInfoForm.getRemark());
            result.setObjectId(id+"");
            result.setObjectType("1");
            result.setType("5");
            result.setStatus(Constants.DATA_STATUS_NOL);
            result.setCreateAt(new Timestamp(System.currentTimeMillis()));
            fcAuditRegisterCore.saveAuditResultEntity(result);
     //   }
        //标删
        AuditCompanyEntity  entity = searchCompanyById(auditExaminationInfoForm.getPid());
        entity.setType("2");//将审核状态改为现场调查录入
        fcAuditCompanyCore.submitExaminationStatus(entity);
        msg.setCode(0);
        msg.setType("audit-companyCore");
        return msg;
    }

    @Override
    public Message submitSurveyInfo(AuditSurveyInfoForm auditSurveyInfoForm) {
        Message msg = new Message();
        AuditSurveyInfoEntity entity = new AuditSurveyInfoEntity();
        if(auditSurveyInfoForm.getAuditSurveyInfoEntity()!=null){
            if(null!=auditSurveyInfoForm.getAuditSurveyInfoEntity().getSurveyTime()){
                entity.setSurveyTime(auditSurveyInfoForm.getAuditSurveyInfoEntity().getSurveyTime());
            }
            if(null!=auditSurveyInfoForm.getAuditSurveyInfoEntity().getQuota()){
                entity.setQuota(auditSurveyInfoForm.getAuditSurveyInfoEntity().getQuota());
            }
            if(null!=auditSurveyInfoForm.getAuditSurveyInfoEntity().getMainBusiness()){
                entity.setMainBusiness(auditSurveyInfoForm.getAuditSurveyInfoEntity().getMainBusiness());
            }
            if(null!=auditSurveyInfoForm.getAuditSurveyInfoEntity().getHistoricalEvolution()){
                entity.setHistoricalEvolution(auditSurveyInfoForm.getAuditSurveyInfoEntity().getHistoricalEvolution());
            }
            if(null!=auditSurveyInfoForm.getAuditSurveyInfoEntity().getModelStrategy()){
                entity.setModelStrategy(auditSurveyInfoForm.getAuditSurveyInfoEntity().getModelStrategy());
            }
            if(null!=auditSurveyInfoForm.getAuditSurveyInfoEntity().getIndustrySituation()){
                entity.setIndustrySituation(auditSurveyInfoForm.getAuditSurveyInfoEntity().getIndustrySituation());
            }
            if(null!=auditSurveyInfoForm.getAuditSurveyInfoEntity().getAffiliatedCompany()){
                entity.setAffiliatedCompany(auditSurveyInfoForm.getAuditSurveyInfoEntity().getAffiliatedCompany());
            }
            if(null!=auditSurveyInfoForm.getAuditSurveyInfoEntity().getAuthenticityStatement()){
                entity.setAuthenticityStatement(auditSurveyInfoForm.getAuditSurveyInfoEntity().getAuthenticityStatement());
            }
            if(null!=auditSurveyInfoForm.getAuditSurveyInfoEntity().getCreditRating()){
                entity.setCreditRating(auditSurveyInfoForm.getAuditSurveyInfoEntity().getCreditRating());
            }
            if(null!=auditSurveyInfoForm.getAuditSurveyInfoEntity().getEquitySituation()){
                entity.setEquitySituation(auditSurveyInfoForm.getAuditSurveyInfoEntity().getEquitySituation());
            }
            if(null!=auditSurveyInfoForm.getAuditSurveyInfoEntity().getWarrantyInformation()){
                entity.setWarrantyInformation(auditSurveyInfoForm.getAuditSurveyInfoEntity().getWarrantyInformation());
            }
            if(null!=auditSurveyInfoForm.getAuditSurveyInfoEntity().getFinancePrediction()){
                entity.setFinancePrediction(auditSurveyInfoForm.getAuditSurveyInfoEntity().getFinancePrediction());
            }
            if(null!=auditSurveyInfoForm.getAuditSurveyInfoEntity().getFinancialAnalysis()){
                entity.setFinancialAnalysis(auditSurveyInfoForm.getAuditSurveyInfoEntity().getFinancialAnalysis());
            }
            if(null!=auditSurveyInfoForm.getAuditSurveyInfoEntity().getAccountsReceivable()){
                entity.setAccountsReceivable(auditSurveyInfoForm.getAuditSurveyInfoEntity().getAccountsReceivable());
            }
            if(null!=auditSurveyInfoForm.getAuditSurveyInfoEntity().getKeyAbnormal()){
                entity.setKeyAbnormal(auditSurveyInfoForm.getAuditSurveyInfoEntity().getKeyAbnormal());
            }
            if(null!=auditSurveyInfoForm.getAuditSurveyInfoEntity().getRemark()){
                entity.setRemark(auditSurveyInfoForm.getAuditSurveyInfoEntity().getRemark());
            }
            entity.setAuditId(auditSurveyInfoForm.getId()+"");//审核id
            entity.setStatus(Constants.DATA_STATUS_NOL);
            entity.setCreateAt(new Timestamp(System.currentTimeMillis()));
        }
        fcAuditCompanyCore.submitSurveyInfo(entity);

        //标删
        String pid = auditSurveyInfoForm.getId()+"";
        String type = auditSurveyInfoForm.getType();
        AuditResultEntity resultEntity = fcAuditCompanyCore.searchResultByObjId(pid,type);
        if(null!=resultEntity){
            resultEntity.setStatus(Constants.DATA_STATUS_DEL);
            fcAuditRegisterCore.updateAuditResult(resultEntity);
        }

        // 添加现场调查录入审核意见
        AuditResultEntity result = new AuditResultEntity();
        result.setObjectId(auditSurveyInfoForm.getId() + "");
            result.setContent(auditSurveyInfoForm.getRemark());
            result.setObjectType("1");
            result.setType("6");
            result.setStatus(Constants.DATA_STATUS_NOL);
            result.setCreateAt(new Timestamp(System.currentTimeMillis()));
            fcAuditRegisterCore.saveAuditResultEntity(result);
      //  }
        //将审核状态改为现场外部网站查询
        AuditCompanyEntity  entity1 = searchCompanyById(auditSurveyInfoForm.getId());
        entity1.setType("3");
        fcAuditCompanyCore.submitExaminationStatus(entity1);
        msg.setType("audit-companyCore");
        msg.setCode(0);
        return msg;
    }

    @Override
    public Message submitWebsiteInfo(AuditWebsiteInfoForm auditWebsiteInfoForm) {
        Message msg = new Message();
        AuditWebsiteInfoEntity entity = new AuditWebsiteInfoEntity();
        if(null!=auditWebsiteInfoForm.getAuditWebsiteInfoEntity()){
            if(null!=auditWebsiteInfoForm.getAuditWebsiteInfoEntity().getGsxt()){
                entity.setGsxt(auditWebsiteInfoForm.getAuditWebsiteInfoEntity().getGsxt());
            }
            if(null!=auditWebsiteInfoForm.getAuditWebsiteInfoEntity().getZhongdengwang()){
                entity.setZhongdengwang(auditWebsiteInfoForm.getAuditWebsiteInfoEntity().getZhongdengwang());
            }
            if(null!=auditWebsiteInfoForm.getAuditWebsiteInfoEntity().getXinxiwang()){
                entity.setXinxiwang(auditWebsiteInfoForm.getAuditWebsiteInfoEntity().getXinxiwang());
            }
            if(null!=auditWebsiteInfoForm.getAuditWebsiteInfoEntity().getFayuan()){
                entity.setFayuan(auditWebsiteInfoForm.getAuditWebsiteInfoEntity().getFayuan());
            }
            if(null!=auditWebsiteInfoForm.getAuditWebsiteInfoEntity().getWangdai()){
                entity.setWangdai(auditWebsiteInfoForm.getAuditWebsiteInfoEntity().getWangdai());
            }
            entity.setAuditId(auditWebsiteInfoForm.getId()+"");//审核id
            entity.setStatus(Constants.DATA_STATUS_NOL);
            entity.setCreateAt(new Timestamp(System.currentTimeMillis()));
        }

        fcAuditCompanyCore.submitWebsiteInfo(entity);

        //外部网站查询审核意见
        if(null==auditWebsiteInfoForm.getRemark()){
            msg.setCode(-1);
            msg.setMessage("审核意见不能为空");
            return msg;
        }
        //标删
        String pid = auditWebsiteInfoForm.getId()+"";
        String type = auditWebsiteInfoForm.getType();
        AuditResultEntity resultEntity = fcAuditCompanyCore.searchResultByObjId(pid,type);
        if(null!=resultEntity){
            resultEntity.setStatus("NEL");
            fcAuditRegisterCore.updateAuditResult(resultEntity);
        }
            //添加外部网站查询审核意见
            AuditResultEntity result = new AuditResultEntity();
            result.setObjectId(auditWebsiteInfoForm.getId() + "");
            result.setContent(auditWebsiteInfoForm.getRemark());
            result.setStatus(Constants.DATA_STATUS_NOL);
            result.setObjectType("1");
            result.setType("7");
            result.setCreateAt(new Timestamp(System.currentTimeMillis()));
            fcAuditRegisterCore.saveAuditResultEntity(result);

        //将审核状态改为输出尽调结果
        AuditCompanyEntity  entity1 = searchCompanyById(auditWebsiteInfoForm.getId());
        entity1.setType("4");
        fcAuditCompanyCore.submitExaminationStatus(entity1);
        msg.setType("audit-companyCore");
        msg.setCode(0);
        return msg;
    }

    @Override
    public Message submitCredit(AuditSubmitCreditForm auditSubmitCreditForm) {
        Message msg = new Message();
        //提交前判断是否已提交
        AuditResultForm form = new AuditResultForm();
        form.setType("8");
        form.setId(Long.valueOf(auditSubmitCreditForm.getId()).toString());
        AuditResultEntity auditResultEntity = fcAuditCompanyCore.searchSurveyOpinion(form);
        if(null!= auditResultEntity){
            msg.setMessage("已提交申请，不可重复提交");
            msg.setCode(-1);
            return msg;
        }

        AuditCompanyEntity  entity = searchCompanyById(auditSubmitCreditForm.getId());
        //添加输出尽调结果审核意见
        AuditResultEntity result = new AuditResultEntity();
        if(auditSubmitCreditForm.getResult().equals("0")){
            entity.setState("-10");//尽调审核不通过
            entity.setSuccessTime(new Timestamp(System.currentTimeMillis())); //尽调审核不通过时间
            result.setResult("0");//审核不通过
            //修改企业表状态
            AuditCompanyEntity companyEntity = searchCompanyById(auditSubmitCreditForm.getId());
            Long enterpriseId = Long.valueOf(companyEntity.getEnterpriseId());
            CompanyEnterpriseEntity entity2 = fcEnterpriseCreditCore.searchCompanyInfo(enterpriseId);
            entity2.setState("30");//授信审核未通过
            fcEnterpriseCreditCore.startCompanyCredit(entity2);
        }
        if(auditSubmitCreditForm.getResult().equals("1")){
            result.setResult("1");//审核通过
            entity.setState("10");//尽调审核通过、审核中
            entity.setEntryStatus("0");//未录入
            entity.setSuccessTime(new Timestamp(System.currentTimeMillis())); //尽调审核通过时间
            entity.setType("5");//待录入
            entity.setAudit("2");//待授信
        }

        //修改审核状态
        fcAuditCompanyCore.submitExaminationStatus(entity);
        result.setObjectId(auditSubmitCreditForm.getId()+"");
        result.setContent(auditSubmitCreditForm.getRemark());
        result.setObjectType("1");
        result.setType("8");
        result.setCreateAt(new Timestamp(System.currentTimeMillis()));
        result.setStatus(Constants.DATA_STATUS_NOL);
        fcAuditRegisterCore.saveAuditResultEntity(result);
        msg.setCode(0);
        msg.setType("audit-companyCore");
        return msg;
    }

    @Override
    public Message searchSurveyStatus(Long id) {
        Message msg = new Message();
        Map type =new HashMap();
        type.put("type",fcAuditCompanyCore.searchSurveyStatus(id));
        msg.setMessage(type);
        msg.setType("audit-companyCore");
        msg.setCode(0);
        return msg;
    }

    @Override
    public Message searchCreditEntry(AuditCompanyEntryForm auditCompanyEntryForm) {
        try{
            if(auditCompanyEntryForm.getEntryStartTime() != null){
                String start = DateUtil.TransformatStartTime(auditCompanyEntryForm.getEntryStartTime());
                auditCompanyEntryForm.setEntryStartTime(DateUtil.formatDate(start,DateUtil.STYLE_1));
            }
            if(auditCompanyEntryForm.getEntryEndTime() != null){
                String end = DateUtil.TransformatEndTime(auditCompanyEntryForm.getEntryEndTime());
                auditCompanyEntryForm.setEntryEndTime(DateUtil.formatDate(end,DateUtil.STYLE_1));
            }
            if(auditCompanyEntryForm.getSubmitStartTime() != null){
                String start = DateUtil.TransformatStartTime(auditCompanyEntryForm.getSubmitStartTime());
                auditCompanyEntryForm.setSubmitStartTime(DateUtil.formatDate(start,DateUtil.STYLE_1));
            }
            if(auditCompanyEntryForm.getSubmitEndTime() != null){
                String end = DateUtil.TransformatEndTime(auditCompanyEntryForm.getSubmitEndTime());
                auditCompanyEntryForm.setSubmitEndTime(DateUtil.formatDate(end,DateUtil.STYLE_1));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        Message msg = new Message();
        PageResponse<Object> pageResponse = new PageResponse<>();
        int total = 0;
        if(auditCompanyEntryForm.getPageNo() != 0){
            total = fcAuditCompanyCore.searchCreditEntryCount(auditCompanyEntryForm);
        }
        List<AuditCompanyEntity> list =  fcAuditCompanyCore.searchCreditEntry(auditCompanyEntryForm);
        if(null!=list){
            pageResponse.setData(list);
            pageResponse.setPageNo(auditCompanyEntryForm.getPageNo());
            pageResponse.setTotal(total);
            pageResponse.setPageSize(auditCompanyEntryForm.getPageSize());
            msg.setMessage(pageResponse);
            msg.setCode(0);
        }else{
            msg.setCode(-1);
        }
        msg.setMessage(pageResponse);
        return msg;
    }

    @Override
    public Message searchAuditCompany(String id) {
        Message msg = new Message();
        Map result = new HashMap();

        AuditCompanyEntity en = auditCompanyService.searchAuditCompanyInfo(Long.valueOf(id));
        String pid = en.getEnterpriseId();
        //查询企业调查意见
        AuditResultForm form = new AuditResultForm();
        form.setType("5");
        form.setId(id);
        AuditResultEntity resultEntity = fcAuditCompanyCore.searchSurveyOpinion(form);
        BaseBankInfoEntity baseBankInfoEntity = fcCompanyEnterpriseCore.searchBaseBankInfo(Long.valueOf(pid));
        CompanyEnterpriseEntity companyEnterpriseEntity = fcCompanyEnterpriseCore.searchCompanyEnterpriseEntity(Long.valueOf(pid));
        CompanyEnterpriseInfoEntity companyEnterpriseInfoEntity =fcCompanyEnterpriseCore.searchCompanyEnterpriseInfoEntity(Long.valueOf(pid));
        List<CompanyOperationEntity> list = fcCompanyEnterpriseCore.searchCompanyOperation(Long.valueOf(pid));
        result.put("baseBankInfoEntity",baseBankInfoEntity);
        result.put("companyEnterpriseEntity",companyEnterpriseEntity);
        result.put("companyEnterpriseInfoEntity",companyEnterpriseInfoEntity);
        result.put("list",list);
        if(null!=resultEntity){
            result.put("opinion", resultEntity.getContent());
        }
        msg.setMessage(result);
        msg.setType("audit-companyCore");
        msg.setCode(0);
        return msg;
    }

    @Override
    public List<RegisterFileEntity> searchRegisterFile(Long pid) {
        return fcRegisterFileCore.searchRegisterFileByOwnerId(pid+"");
    }

    @Override
    public Message searchSurveyInfo(String id) {
        Message msg = new Message();
        Map result = new HashMap();
        //查询尽职调查意见
        AuditResultForm form = new AuditResultForm();
        form.setType("6");
        form.setId(id);
        AuditResultEntity resultEntity = fcAuditCompanyCore.searchSurveyOpinion(form);
        //查询尽职调查详情
        AuditSurveyInfoEntity  entity =  fcAuditCompanyCore.searchSurveyInfo(id);
        if(null!=resultEntity){
            result.put("opinion", resultEntity.getContent());
        }
        result.put("entity",entity);
        msg.setMessage(result);
        msg.setType("audit-companyCore");
        msg.setCode(0);
        return msg;
    }


    @Override
    public Message searchCreditReview(String id) {
        Message msg = new Message();
        Map result = new HashMap();
        //查询尽职调查意见
        AuditResultForm form = new AuditResultForm();
        form.setType("8");
        form.setId(id);
        AuditResultEntity resultEntity = fcAuditCompanyCore.searchSurveyOpinion(form);
        if(null != resultEntity){
            msg.setMessage(resultEntity);
        }
        msg.setType("audit-companyCore");
        msg.setCode(0);
        return msg;
    }

    @Override
    public Message searchWebsiteInfo(String id) {
        Message msg = new Message();
        Map result = new HashMap();
        //查询外部网站调查意见
        AuditResultForm form = new AuditResultForm();
        form.setType("7");
        form.setId(id);
        AuditResultEntity resultEntity = fcAuditCompanyCore.searchSurveyOpinion(form);
        AuditWebsiteInfoEntity entity = fcAuditCompanyCore.searchWebsiteInfo(id);
        if(null != resultEntity){
            result.put("opinion", resultEntity.getContent());
        }
        result.put("entity",entity);
        msg.setCode(0);
        msg.setType("audit-companyCore");
        msg.setMessage(result);
        return msg;
    }

    @Override
    public Message submitCreditReview(AuditCreditReviewForm auditCreditReviewForm) {
        Message msg = new Message();
        String pid = auditCreditReviewForm.getPid().toString();
        int creditCount = fcAuditCompanyCore.searchCreditInfoCount(pid);
        if(creditCount>0){
            msg.setMessage("录入申请已提交，不可重复提交");
            msg.setCode(-1);
            msg.setType("audit-companyCore");
            return msg;
        }
        AuditCreditInfoEntity entity = new AuditCreditInfoEntity();
       /* if(auditCreditReviewForm.getProductType().equals("reverseRecourse")){*/
            ProCategoryEntity proCategoryEntity = fcProContractCore.searchCategoryByCategory(auditCreditReviewForm.getProductType());
            entity.setProductCode(proCategoryEntity.getName());
        /*}*/
       /* if(auditCreditReviewForm.getProductType().equals("reverseNoRecourse")){
            ProCategoryEntity proCategoryEntity = fcProContractCore.searchCategoryByCategory(auditCreditReviewForm.getProductType());
            entity.setProductCode(proCategoryEntity.getName());
        }*/
        entity.setAuditCompanyId(auditCreditReviewForm.getPid());
        entity.setName(auditCreditReviewForm.getName());
        entity.setProductType(auditCreditReviewForm.getProductType());
        entity.setContractNo(auditCreditReviewForm.getContractNo());
        entity.setCreditStatus(auditCreditReviewForm.getCreditStatus());
        entity.setCycleSign(auditCreditReviewForm.getCycleSign());
        entity.setAmount(auditCreditReviewForm.getAmount());
        entity.setEffectiveStartTime(auditCreditReviewForm.getEffectiveStartTime());
        entity.setEffectiveEndTime(auditCreditReviewForm.getEffectiveEndTime());
        entity.setProductNo(auditCreditReviewForm.getProductNo());
        boolean flag = fcAuditCompanyCore.submitCreditReview(entity);

        AuditCompanyEntity  entity1 = searchCompanyById(auditCreditReviewForm.getPid());
        entity1.setEntryStatus("1");
        entity1.setEntryTime(new Date());
        entity1.setAuditStatus("0");//待授信
        fcAuditCompanyCore.submitExaminationStatus(entity1);
        msg.setMessage(flag);
        msg.setCode(0);
        return msg;
    }

    @Override
    public  AuditCreditInfoEntity  searchCreditInfo(Long id) {
        List<AuditCreditInfoEntity> list = fcAuditCompanyCore.searchCreditInfo(id);
        AuditCreditInfoEntity entity = list.get(0);
        return entity;
    }

    @Override
    public Message searchCreditList(AuditCreditListForm auditCreditListForm) {
        try{
            if(auditCreditListForm.getSurveyStartTime() != null){
                String start = DateUtil.TransformatStartTime(auditCreditListForm.getSurveyStartTime());
                auditCreditListForm.setSurveyStartTime(DateUtil.formatDate(start,DateUtil.STYLE_1));
            }
            if(auditCreditListForm.getSurveyEndTime() != null){
                String end = DateUtil.TransformatEndTime(auditCreditListForm.getSurveyEndTime());
                auditCreditListForm.setSurveyEndTime(DateUtil.formatDate(end,DateUtil.STYLE_1));
            }
            if(auditCreditListForm.getSubmitStartTime() != null){
                String start = DateUtil.TransformatStartTime(auditCreditListForm.getSubmitStartTime());
                auditCreditListForm.setSubmitStartTime(DateUtil.formatDate(start,DateUtil.STYLE_1));
            }
            if(auditCreditListForm.getSubmitEndTime() != null){
                String end = DateUtil.TransformatEndTime(auditCreditListForm.getSubmitEndTime());
                auditCreditListForm.setSubmitEndTime(DateUtil.formatDate(end,DateUtil.STYLE_1));
            }
        }catch (Exception e){

        }
        Message msg = new Message();
        PageResponse<Object> pageResponse = new PageResponse<>();
        int total = 0;
        if(auditCreditListForm.getPageNo()!=0){
            total = fcAuditCompanyCore.searchCreditListCount(auditCreditListForm);
        }
        List<AuditCompanyEntity> list = fcAuditCompanyCore.searchCreditList(auditCreditListForm);
        if(null!=list){
            pageResponse.setData(list);
            pageResponse.setTotal(total);
            pageResponse.setPageNo(auditCreditListForm.getPageNo());
            pageResponse.setPageSize(auditCreditListForm.getPageSize());
            msg.setMessage(pageResponse);
            msg.setCode(0);
        }else{
            msg.setCode(-1);
        }
        return msg;
    }

    @Override
    public Message submitCreditReviewResult(AuditCreditReviewForm auditCreditReviewForm) {
        Message msg = new Message();
        List<AuditCreditInfoEntity> list = fcAuditCompanyCore.searchCreditInfo(auditCreditReviewForm.getPid());
        AuditCreditInfoEntity entity = list.get(0);
        entity.setName(auditCreditReviewForm.getName());
        entity.setProductType(auditCreditReviewForm.getProductType());
        entity.setContractNo(auditCreditReviewForm.getContractNo());
        entity.setCreditStatus(auditCreditReviewForm.getCreditStatus());
        entity.setCycleSign(auditCreditReviewForm.getCycleSign());
        entity.setAmount(auditCreditReviewForm.getAmount());
        entity.setEffectiveStartTime(auditCreditReviewForm.getEffectiveStartTime());
        entity.setEffectiveEndTime(auditCreditReviewForm.getEffectiveEndTime());
        entity.setPid(entity.getPid());
        entity.setProductNo(auditCreditReviewForm.getProductNo());
        fcAuditCompanyCore.updateCreditReview(entity);

        //修改授信审核表状态
        AuditCompanyEntity entity1 = searchCompanyById(auditCreditReviewForm.getPid());
        entity1.setAuditStatus("1");//已授信
        entity1.setType("7");//申请成功
        entity1.setAuditTime(new Date());
        fcAuditCompanyCore.submitExaminationStatus(entity1);

        //修改企业表状态
        AuditCompanyEntity companyEntity = searchCompanyById(auditCreditReviewForm.getPid());
        Long enterpriseId = Long.valueOf(companyEntity.getEnterpriseId());
        CompanyEnterpriseEntity entity2 = fcEnterpriseCreditCore.searchCompanyInfo(enterpriseId);
        entity2.setState("40");//授信审核通过
        fcEnterpriseCreditCore.startCompanyCredit(entity2);
        msg.setCode(0);
        return msg;
    }


    @Override
    public AuditCompanyEntity searchCompanyById(Long pid){
      return fcAuditCompanyCore.searchCompanyById(pid);
    }

    @Override
    public Message updateAllResult(AuditExaminationForm form) {
        Message msg = new Message();
        String pid  = form.getId()+"";
        String type = form.getType();
        AuditResultEntity resultEntity = fcAuditCompanyCore.searchResultByObjId(pid,type);
        if(resultEntity.getContent().equals(form.getRemark())){
            msg.setCode(0);
            return msg;
        }
        resultEntity.setContent(form.getRemark());
        fcAuditRegisterCore.updateAuditResult(resultEntity);
        msg.setCode(0);
        return msg;
    }


}
