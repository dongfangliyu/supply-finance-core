package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.feign;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.*;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.AuditCreditListForm;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.AuditCreditReviewForm;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.UpdateAuditResultForm;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditCompanyEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditCreditInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.RegisterFileEntity;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author WuTianJuan
 * @Date Created in 17:39 2018/7/5
 */
@FeignClient(name = "loan-manager-service")
public interface FCAuditCompanyService {

    @ResponseBody
    @RequestMapping(value = "/audit/companyService/searchAuditCompanySurvey", method = RequestMethod.POST)
    Message searchAuditCompanySurvey(@RequestBody AuditCompanyForm auditCompanyForm);

    @ResponseBody
    @RequestMapping(value = "/audit/companyService/searchAuditCompanyInfo", method = RequestMethod.GET)
    AuditCompanyEntity searchAuditCompanyInfo(@RequestParam(value = "id") Long id);

    @ResponseBody
    @RequestMapping(value = "/audit/companyService/submitExaminationStatus", method = RequestMethod.POST)
    Message submitExaminationStatus(@RequestBody AuditExaminationForm examine);

    @ResponseBody
    @RequestMapping(value = "/audit/companyService/submitExaminationInfo", method = RequestMethod.POST)
    Message submitExaminationInfo(@RequestBody AuditExaminationInfoForm auditExaminationInfoForm);

    @ResponseBody
    @RequestMapping(value = "/audit/companyService/submitSurveyInfo", method = RequestMethod.POST)
    Message submitSurveyInfo(@RequestBody AuditSurveyInfoForm auditSurveyInfoForm);

    @ResponseBody
    @RequestMapping(value = "/audit/companyService/submitWebsiteInfo", method = RequestMethod.POST)
    Message submitWebsiteInfo(@RequestBody AuditWebsiteInfoForm auditWebsiteInfoForm);

    @ResponseBody
    @RequestMapping(value = "/audit/companyService/submitCredit", method = RequestMethod.POST)
    Message submitCredit(@RequestBody AuditSubmitCreditForm auditSubmitCreditForm);

    @ResponseBody
    @RequestMapping(value = "/audit/companyService/searchSurveyStatus", method = RequestMethod.GET)
    Message searchSurveyStatus(@RequestParam(value = "id") Long id);

    @ResponseBody
    @RequestMapping(value = "/audit/companyService/searchResultList", method = RequestMethod.POST)
    Message searchResultList(@RequestBody AuditSearchResultListForm auditSearchResultListForm);

    @ResponseBody
    @RequestMapping(value = "/audit/companyService/searchCreditEntry", method = RequestMethod.POST)
    Message searchCreditEntry(@RequestBody AuditCompanyEntryForm auditCompanyEntryForm);

    @ResponseBody
    @RequestMapping(value = "/audit/companyService/searchAuditCompany", method = RequestMethod.GET)
    Message searchAuditCompany(@RequestParam(value = "id") String id);

    @ResponseBody
    @RequestMapping(value = "/audit/companyService/searchAuditCompanyIn", method = RequestMethod.GET)
    AuditCompanyEntity searchAuditCompanyIn(@RequestParam(value = "id") Long id);

    @ResponseBody
    @RequestMapping(value = "/audit/companyService/searchRegisterFile", method = RequestMethod.GET)
    List<RegisterFileEntity> searchRegisterFile(@RequestParam(value = "pid") Long pid);

    @ResponseBody
    @RequestMapping(value = "/audit/companyService/searchSurveyInfo", method = RequestMethod.GET)
    Message searchSurveyInfo(@RequestParam(value = "id") String id);

    @ResponseBody
    @RequestMapping(value = "/audit/companyService/searchWebsiteInfo", method = RequestMethod.GET)
    Message searchWebsiteInfo(@RequestParam(value = "id") String id);

    @ResponseBody
    @RequestMapping(value = "/audit/companyService/submitCreditReview", method = RequestMethod.POST)
    Message submitCreditReview(@RequestBody  AuditCreditReviewForm auditCreditReviewForm);

    @ResponseBody
    @RequestMapping(value = "/audit/companyService/searchCreditInfo", method = RequestMethod.GET)
    AuditCreditInfoEntity searchCreditInfo(@RequestParam(value = "id") Long id);

    @ResponseBody
    @RequestMapping(value = "/audit/companyService/searchCreditList", method = RequestMethod.GET)
    Message searchCreditList(@RequestBody AuditCreditListForm auditCreditListForm);

    @ResponseBody
    @RequestMapping(value = "/audit/companyService/submitCreditReviewResult", method = RequestMethod.POST)
    Message submitCreditReviewResult(@RequestBody AuditCreditReviewForm auditCreditReviewForm);

    @ResponseBody
    @RequestMapping(value = "/audit/companyService/updateAllResult", method = RequestMethod.POST)
    Message updateAllResult(@RequestBody AuditExaminationForm form);

    @ResponseBody
    @RequestMapping(value = "/audit/companyService/searchCreditReview", method = RequestMethod.GET)
    Message searchCreditReview(@RequestParam(value = "id") String id);
}
