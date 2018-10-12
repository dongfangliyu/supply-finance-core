package cn.fintecher.supply.finance.loan.manager.service.audit.feign;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.*;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.AuditCreditListForm;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.AuditCreditReviewForm;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.AuditResultForm;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.UpdateAuditResultForm;
import cn.fintecher.supply.finance.loan.manager.common.model.*;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author WuTianJuan
 * @Date Created in 18:00 2018/7/5
        */
@FeignClient(name = "loan-manager-core")
public interface FCAuditCompanyCore {
    @ResponseBody
    @RequestMapping(value = "/audit/companyCore/searchAuditCompanySurvey", method = RequestMethod.POST)
    List<AuditCompanyEntity> searchAuditCompanySurvey(@RequestBody AuditCompanyForm auditCompanyForm);

    @ResponseBody
    @RequestMapping(value = "/audit/companyCore/searchAuditCompanyInfo", method = RequestMethod.GET)
    AuditCompanyEntity searchAuditCompanyInfo(@RequestParam(value = "id") Long id);

    @ResponseBody
    @RequestMapping(value = "/audit/companyCore/submitExaminationStatus", method = RequestMethod.POST)
    Boolean submitExaminationStatus(@RequestBody AuditCompanyEntity entity);

    @ResponseBody
    @RequestMapping(value = "/audit/companyCore/submitExaminationInfo", method = RequestMethod.POST)
    Boolean submitExaminationInfo(@RequestBody AuditResultEntity entity);

    @ResponseBody
    @RequestMapping(value = "/audit/companyCore/submitSurveyInfo", method = RequestMethod.POST)
    Boolean submitSurveyInfo(@RequestBody AuditSurveyInfoEntity entity);

    @ResponseBody
    @RequestMapping(value = "/audit/companyCore/submitWebsiteInfo", method = RequestMethod.POST)
    Boolean submitWebsiteInfo(@RequestBody AuditWebsiteInfoEntity entity);

    @ResponseBody
    @RequestMapping(value = "/audit/companyCore/submitCredit", method = RequestMethod.POST)
    Boolean submitCredit(@RequestBody AuditCompanyEntity entity);

    @ResponseBody
    @RequestMapping(value ="/audit/companyCore/searchAuditCompanyListCount", method = RequestMethod.POST)
    int searchAuditCompanyListCount(@RequestBody AuditCompanyForm auditCompanyForm);

    @ResponseBody
    @RequestMapping(value ="/audit/companyCore/updateAuditCompanyEntity", method = RequestMethod.POST)
    Boolean updateAuditCompanyEntity(@RequestBody AuditCompanyEntity entity);

    @ResponseBody
    @RequestMapping(value ="/audit/companyCore/searchSurveyStatus", method = RequestMethod.GET)
    String searchSurveyStatus(@RequestParam(value = "id") Long id);

    @ResponseBody
    @RequestMapping(value ="/audit/companyCore/searchAuditResult", method = RequestMethod.POST)
    AuditResultEntity searchAuditResult(@RequestBody AuditResultEntity result);

    @ResponseBody
    @RequestMapping(value ="/audit/companyCore/searchResultList", method = RequestMethod.POST)
    List<AuditResultEntity> searchResultList(@RequestBody AuditSearchResultListForm auditSearchResultListForm);

    @ResponseBody
    @RequestMapping(value ="/audit/companyCore/searchCreditEntry", method = RequestMethod.POST)
    List<AuditCompanyEntity> searchCreditEntry(@RequestBody AuditCompanyEntryForm auditCompanyEntryForm);

    @ResponseBody
    @RequestMapping(value ="/audit/companyCore/searchCreditEntryCount", method = RequestMethod.POST)
    int searchCreditEntryCount(@RequestBody AuditCompanyEntryForm auditCompanyEntryForm);

    @ResponseBody
    @RequestMapping(value ="/audit/companyCore/searchAllResultList", method = RequestMethod.GET)
    List<AuditResultEntity> searchAllResultList(@RequestParam(value="objectId") String objectId);

    @ResponseBody
    @RequestMapping(value ="/audit/companyCore/searchSurveyInfo", method = RequestMethod.GET)
    AuditSurveyInfoEntity  searchSurveyInfo(@RequestParam(value = "id") String id);

    @ResponseBody
    @RequestMapping(value ="/audit/companyCore/searchSurveyOpinion", method = RequestMethod.POST)
    AuditResultEntity searchSurveyOpinion(@RequestBody AuditResultForm form);

    @ResponseBody
    @RequestMapping(value ="/audit/companyCore/searchWebsiteInfo", method = RequestMethod.GET)
    AuditWebsiteInfoEntity searchWebsiteInfo(@RequestParam(value = "id") String id);

    @ResponseBody
    @RequestMapping(value ="/audit/companyCore/submitCreditReview", method = RequestMethod.POST)
    Boolean submitCreditReview(@RequestBody  AuditCreditInfoEntity entity);

    @ResponseBody
    @RequestMapping(value ="/audit/companyCore/searchCreditInfo", method = RequestMethod.GET)
    List<AuditCreditInfoEntity> searchCreditInfo(@RequestParam(value = "id") Long id);

    @ResponseBody
    @RequestMapping(value ="/audit/companyCore/searchCreditList", method = RequestMethod.POST)
    List<AuditCompanyEntity> searchCreditList(@RequestBody AuditCreditListForm auditCreditListForm);

    @ResponseBody
    @RequestMapping(value ="/audit/companyCore/searchCreditListCount", method = RequestMethod.POST)
    int searchCreditListCount(@RequestBody AuditCreditListForm auditCreditListForm);

    @ResponseBody
    @RequestMapping(value ="/audit/companyCore/submitCreditReviewResult", method = RequestMethod.POST)
    Message submitCreditReviewResult(@RequestBody AuditCreditReviewForm auditCreditReviewForm);

    @ResponseBody
    @RequestMapping(value ="/audit/companyCore/searchCompanyById", method = RequestMethod.GET)
    AuditCompanyEntity searchCompanyById(@RequestParam(value="pid") Long pid);

    @ResponseBody
    @RequestMapping(value ="/audit/companyCore/searchResultByObjId", method = RequestMethod.GET)
    AuditResultEntity searchResultByObjId(@RequestParam(value="pid")String pid,@RequestParam(value="type") String type);

    @ResponseBody
    @RequestMapping(value ="/audit/companyCore/updateCreditReview", method = RequestMethod.POST)
    Boolean updateCreditReview(@RequestBody AuditCreditInfoEntity entity);

    /**
     * 根据核心企业id，查询授信信息
     * 胡进宝
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/audit/companyCore/searchEntityByEnterpriseId", method = RequestMethod.GET)
    AuditCreditInfoEntity searchEntityByEnterpriseId(@RequestParam(value="pid")Long pid);

    @ResponseBody
    @RequestMapping(value = "/audit/companyCore/searchAuditCreditCount", method = RequestMethod.GET)
    int searchAuditCreditCount(@RequestParam(value="auditStatus") String auditStatus);

    @ResponseBody
    @RequestMapping(value = "/audit/companyCore/searchCreditInfoCount", method = RequestMethod.GET)
    int searchCreditInfoCount(@RequestParam(value="pid")String pid);
}
