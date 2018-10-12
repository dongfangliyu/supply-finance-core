package cn.fintecher.supply.finance.loan.manager.service.audit.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.*;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.AuditCreditListForm;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.AuditCreditReviewForm;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.UpdateAuditResultForm;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditCompanyEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditCreditInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.RegisterFileEntity;
import cn.fintecher.supply.finance.loan.manager.service.audit.service.AuditCompanyService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author WuTianJuan
 * @Date Created in 17:58 2018/7/5
 */
@RestController
@RequestMapping("/audit/companyService")
public class AuditCompanyController {

    @Autowired
    private AuditCompanyService auditCompanyService;

    @ResponseBody
    @RequestMapping(value ="/searchAuditCompanySurvey", method = RequestMethod.POST)
    public Message searchAuditCompanySurvey(@RequestBody AuditCompanyForm auditCompanyForm){
        return auditCompanyService.searchAuditCompanySurvey(auditCompanyForm);
    }

    @ResponseBody
    @RequestMapping(value ="/searchAuditCompanyInfo", method = RequestMethod.GET)
    public AuditCompanyEntity searchAuditCompanyInfo(@RequestParam(value="id") Long id){
        return auditCompanyService.searchAuditCompanyInfo(id);
    }

    @ResponseBody
    @RequestMapping(value ="/submitExaminationStatus", method = RequestMethod.POST)
    public Message submitExaminationStatus(@RequestBody AuditExaminationForm examine){
        return auditCompanyService.submitExaminationStatus(examine);
    }

    @ResponseBody
    @RequestMapping(value ="/submitExaminationInfo", method = RequestMethod.POST)
    public Message submitExaminationInfo(@RequestBody AuditExaminationInfoForm auditExaminationInfoForm){
        return auditCompanyService.submitExaminationInfo(auditExaminationInfoForm);
    }

    @ResponseBody
    @RequestMapping(value ="/submitSurveyInfo", method = RequestMethod.POST)
    public Message submitSurveyInfo(@RequestBody AuditSurveyInfoForm auditSurveyInfoForm){
        return auditCompanyService.submitSurveyInfo(auditSurveyInfoForm);
    }

    @ResponseBody
    @RequestMapping(value ="/submitWebsiteInfo", method = RequestMethod.POST)
    public Message submitWebsiteInfo(@RequestBody AuditWebsiteInfoForm auditWebsiteInfoForm){
        return auditCompanyService.submitWebsiteInfo(auditWebsiteInfoForm);
    }

    @ResponseBody
    @RequestMapping(value ="/submitCredit", method = RequestMethod.POST)
    public Message submitCredit(@RequestBody AuditSubmitCreditForm auditSubmitCreditForm){
        return auditCompanyService.submitCredit(auditSubmitCreditForm);
    }

    @ResponseBody
    @RequestMapping(value ="/searchSurveyStatus", method = RequestMethod.GET)
    public Message searchSurveyStatus(@RequestParam(value="id") Long id){
        return auditCompanyService.searchSurveyStatus(id);
    }

    @ResponseBody
    @RequestMapping(value ="/searchCreditEntry", method = RequestMethod.POST)
    public Message searchCreditEntry(@RequestBody AuditCompanyEntryForm auditCompanyEntryForm){
        return auditCompanyService.searchCreditEntry(auditCompanyEntryForm);
    }

    @ResponseBody
    @RequestMapping(value ="/searchAuditCompany", method = RequestMethod.GET)
    public Message searchAuditCompany(@RequestParam(value="id") String id){
        return auditCompanyService.searchAuditCompany(id);
    }

    @ResponseBody
    @RequestMapping(value = "/searchAuditCompanyIn", method = RequestMethod.GET)
    public AuditCompanyEntity searchAuditCompanyIn(@RequestParam(value = "id") Long id){
        return auditCompanyService.searchAuditCompanyInfo(id);
    }

    @ResponseBody
    @RequestMapping(value = "/searchRegisterFile", method = RequestMethod.GET)
    public List<RegisterFileEntity> searchRegisterFile(@RequestParam(value = "pid") Long pid){
        return auditCompanyService.searchRegisterFile(pid);
    }

    @ResponseBody
    @RequestMapping(value ="/searchSurveyInfo", method = RequestMethod.GET)
    public Message searchSurveyInfo(@RequestParam(value="id") String id){
        return auditCompanyService.searchSurveyInfo(id);
    }

    @ResponseBody
    @RequestMapping(value ="/searchWebsiteInfo", method = RequestMethod.GET)
    public Message searchWebsiteInfo(@RequestParam(value="id") String id){
        return auditCompanyService.searchWebsiteInfo(id);
    }

    @ResponseBody
    @RequestMapping(value ="/submitCreditReview", method = RequestMethod.POST)
    public Message submitCreditReview(@RequestBody AuditCreditReviewForm auditCreditReviewForm){
        return auditCompanyService.submitCreditReview(auditCreditReviewForm);
    }

    @ResponseBody
    @RequestMapping(value ="/searchCreditInfo", method = RequestMethod.GET)
    public AuditCreditInfoEntity searchCreditInfo(@RequestParam(value = "id") Long id){
        return auditCompanyService.searchCreditInfo(id);
    }

    @ResponseBody
    @RequestMapping(value ="/searchCreditList", method = RequestMethod.POST)
    public Message searchCreditList(@RequestBody AuditCreditListForm auditCreditListForm){
        return auditCompanyService.searchCreditList(auditCreditListForm);
    }

    @ResponseBody
    @RequestMapping(value ="/submitCreditReviewResult", method = RequestMethod.POST)
    public Message submitCreditReviewResult(@RequestBody AuditCreditReviewForm auditCreditReviewForm){
        return auditCompanyService.submitCreditReviewResult(auditCreditReviewForm);
    }

    @ResponseBody
    @RequestMapping(value ="/updateAllResult", method = RequestMethod.POST)
    public Message updateAllResult(@RequestBody AuditExaminationForm form){
        return auditCompanyService.updateAllResult(form);
    }

    @ResponseBody
    @RequestMapping(value = "/searchCreditReview", method = RequestMethod.GET)
    public Message searchCreditReview(@RequestParam(value = "id") String id){
        return auditCompanyService.searchCreditReview(id);
    }
}