package cn.fintecher.supply.finance.loan.manager.core.audit.controller;

import cn.fintecher.supply.finance.loan.manager.common.form.AuditCompanyEntryForm;
import cn.fintecher.supply.finance.loan.manager.common.form.AuditCompanyForm;
import cn.fintecher.supply.finance.loan.manager.common.form.AuditSearchResultListForm;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.AuditCreditListForm;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.AuditResultForm;
import cn.fintecher.supply.finance.loan.manager.common.form.guarantee.AuditFrontGuaranteeListForm;
import cn.fintecher.supply.finance.loan.manager.common.model.*;
import cn.fintecher.supply.finance.loan.manager.core.audit.service.AuditCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author WuTianJuan
 * @Date Created in 18:11 2018/7/5
 */
@RestController
@RequestMapping("/audit/companyCore")
public class AuditCompanyController {

    @Autowired
    private AuditCompanyService auditCompanyService;

    @ResponseBody
    @RequestMapping(value ="/searchAuditCompanySurvey", method = RequestMethod.POST)
    public List<AuditCompanyEntity> searchAuditCompanySurvey(@RequestBody AuditCompanyForm auditCompanyForm){
        return auditCompanyService.searchAuditCompanySurvey(auditCompanyForm);
    }

    @ResponseBody
    @RequestMapping(value ="/searchAuditCompanyInfo", method = RequestMethod.GET)
    public AuditCompanyEntity searchAuditCompanyInfo(@RequestParam(value="id") Long id){
        return auditCompanyService.searchAuditCompanyInfo(id);
    }

    @ResponseBody
    @RequestMapping(value ="/submitExaminationStatus", method = RequestMethod.POST)
    public Boolean submitExaminationStatus(@RequestBody AuditCompanyEntity entity){
        return auditCompanyService.submitExaminationStatus(entity);
    }

    @ResponseBody
    @RequestMapping(value ="/submitSurveyInfo", method = RequestMethod.POST)
    public Boolean submitSurveyInfo(@RequestBody AuditSurveyInfoEntity entity){
        return auditCompanyService.submitSurveyInfo(entity);
    }

    @ResponseBody
    @RequestMapping(value ="/submitWebsiteInfo", method = RequestMethod.POST)
    public Boolean submitWebsiteInfo(@RequestBody AuditWebsiteInfoEntity entity){
        return auditCompanyService.submitWebsiteInfo(entity);
    }

    @ResponseBody
    @RequestMapping(value = "submitCredit", method = RequestMethod.POST)
    public Boolean submitCredit(@RequestBody AuditCompanyEntity entity){
        return auditCompanyService.submitCredit(entity);
    }

    @ResponseBody
    @RequestMapping(value ="/searchAuditCompanyListCount", method = RequestMethod.POST)
    public int searchAuditCompanyListCount(@RequestBody AuditCompanyForm auditCompanyForm){
        return auditCompanyService.searchAuditCompanyListCount(auditCompanyForm);
    }

    @ResponseBody
    @RequestMapping(value ="/updateAuditCompanyEntity", method = RequestMethod.POST)
    public Boolean updateAuditCompanyEntity(@RequestBody AuditCompanyEntity entity){
        return auditCompanyService.updateAuditCompanyEntity(entity);
    }

    @ResponseBody
    @RequestMapping(value ="/searchSurveyStatus", method = RequestMethod.GET)
    public String searchSurveyStatus(@RequestParam(value="id") Long id){
        return auditCompanyService.searchSurveyStatus(id);
    }

    @ResponseBody
    @RequestMapping(value ="/searchAuditResult", method = RequestMethod.POST)
    public AuditResultEntity searchAuditResult(@RequestBody AuditResultEntity auditResultEntity){
        return auditCompanyService.searchAuditResult(auditResultEntity);
    }

    @ResponseBody
    @RequestMapping(value ="/searchResultList", method = RequestMethod.POST)
    public List<AuditResultEntity> searchResultList(@RequestBody AuditSearchResultListForm auditSearchResultListForm){
        return auditCompanyService.searchResultList(auditSearchResultListForm);
    }

    @ResponseBody
    @RequestMapping(value ="/searchCreditEntry", method = RequestMethod.POST)
    public List<AuditCompanyEntity> searchCreditEntry(@RequestBody AuditCompanyEntryForm auditCompanyEntryForm){
        return auditCompanyService.searchCreditEntry(auditCompanyEntryForm);
    }

    @ResponseBody
    @RequestMapping(value ="/searchCreditEntryCount", method = RequestMethod.POST)
    public int searchCreditEntryCount(@RequestBody AuditCompanyEntryForm auditCompanyEntryForm){
        return auditCompanyService.searchCreditEntryCount(auditCompanyEntryForm);
    }

    @ResponseBody
    @RequestMapping(value ="/searchAllResultList", method = RequestMethod.GET)
    public List<AuditResultEntity> searchAllResultList(@RequestParam(value="objectId") String objectId){
        return auditCompanyService.searchAllResultList(objectId);
    }

    @ResponseBody
    @RequestMapping(value ="/searchSurveyInfo", method = RequestMethod.GET)
    public AuditSurveyInfoEntity searchSurveyInfo(@RequestParam(value = "id") String id){
        return auditCompanyService.searchSurveyInfo(id);
    }

    @ResponseBody
    @RequestMapping(value ="/searchSurveyOpinion", method = RequestMethod.POST)
    public AuditResultEntity searchSurveyOpinion(@RequestBody AuditResultForm form){
        return auditCompanyService.searchSurveyOpinion(form);
    }

    @ResponseBody
    @RequestMapping(value ="/searchWebsiteInfo", method = RequestMethod.GET)
    public AuditWebsiteInfoEntity searchWebsiteInfo(@RequestParam(value = "id") String id){
        return auditCompanyService.searchWebsiteInfo(id);
    }

    @ResponseBody
    @RequestMapping(value ="/submitCreditReview", method = RequestMethod.POST)
    public Boolean submitCreditReview(@RequestBody AuditCreditInfoEntity entity){
        return auditCompanyService.submitCreditReview(entity);
    }

    @ResponseBody
    @RequestMapping(value ="/searchCreditInfo", method = RequestMethod.GET)
    public List<AuditCreditInfoEntity> searchCreditInfo(@RequestParam(value = "id") Long id){
        return auditCompanyService.searchCreditInfo(id);
    }

    @ResponseBody
    @RequestMapping(value ="/searchCreditList", method = RequestMethod.POST)
    public List<AuditCompanyEntity> searchCreditList(@RequestBody AuditCreditListForm auditCreditListForm){
        return auditCompanyService.searchCreditList(auditCreditListForm);
    }

    @ResponseBody
    @RequestMapping(value ="/searchCreditListCount", method = RequestMethod.POST)
    public int searchCreditListCount(@RequestBody AuditCreditListForm auditCreditListForm){
        return auditCompanyService.searchCreditListCount(auditCreditListForm);
    }

    @ResponseBody
    @RequestMapping(value = "/searchCompanyById", method = RequestMethod.GET)
    public AuditCompanyEntity searchCompanyById(@RequestParam(value="pid") Long pid){
        return auditCompanyService.searchCompanyById(pid);
    }

    @ResponseBody
    @RequestMapping(value = "/searchResultByObjId", method = RequestMethod.GET)
    public AuditResultEntity searchResultByObjId(@RequestParam(value="pid")String pid,@RequestParam(value="type") String type){
        return auditCompanyService.searchResultByObjId(pid,type);
    }

    @ResponseBody
    @RequestMapping(value ="/updateCreditReview", method = RequestMethod.POST)
    public Boolean updateCreditReview(@RequestBody AuditCreditInfoEntity entity){
        return auditCompanyService.updateCreditReview(entity);
    }


    /**
     * 根据核心企业id，查询授信信息
     * 胡进宝
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/searchEntityByEnterpriseId", method = RequestMethod.GET)
    public AuditCreditInfoEntity searchEntityByEnterpriseId(@RequestParam(value="pid")Long pid){
        return auditCompanyService.getEntityByEnterpriseId(pid);
    }

    @ResponseBody
    @RequestMapping(value = "/searchAuditCreditCount", method = RequestMethod.GET)
    public int searchAuditCreditCount(@RequestParam(value="auditStatus") String auditStatus){
        return auditCompanyService.searchAuditCreditCount(auditStatus);
    }

    @ResponseBody
    @RequestMapping(value = "/searchCreditInfoCount", method = RequestMethod.GET)
    public int searchCreditInfoCount(@RequestParam(value="pid")String pid){
        return auditCompanyService.searchCreditInfoCount(pid);
    }
}
