package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.*;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.AuditCreditListForm;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.AuditCreditReviewForm;
import cn.fintecher.supply.finance.loan.manager.common.model.*;
import cn.fintecher.supply.finance.loan.manager.pm.bff.Base.controller.BaseController;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.feign.FCAuditFileService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.AuditCompanyService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.GuaranteeManagerService;
import com.google.common.base.Strings;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author WuTianJuan
 * @Date Created in 17:35 2018/7/5
 */
@RestController
@RequestMapping("/audit/auditCompany")
@Api(tags ="尽调、授信审核")
public class AuditCompanyController extends BaseController {

    @Autowired
    private AuditCompanyService auditCompanyService;

    @Autowired
    private FCAuditFileService fcAuditFileService;

    @Autowired
    private GuaranteeManagerService guaranteeManagerService;

    @ApiOperation(value = "查询企业尽调审核列表 ", notes = "查询企业尽调审核列表")
    @RequestMapping(value = "/searchAuditCompanySurvey", method = RequestMethod.POST)
    public Message searchAuditCompanySurvey(@RequestBody AuditCompanyForm auditCompanyForm) {
        return auditCompanyService.searchAuditCompanySurvey(auditCompanyForm);
    }


    @ApiOperation(value = "查询企业基本信息", notes = "查询企业基本信息")
    @RequestMapping(value = "/searchAuditCompany", method = RequestMethod.GET)
    public Message searchAuditCompanyInfo(@RequestParam(value = "id") String id) {
        return auditCompanyService.searchAuditCompany(id);
    }

    @ApiOperation(value = "查询财务报表信息", notes = "查询财务报表信息")
    @ResponseBody
    @RequestMapping(value = "/searchAccountingStatementInfo", method = RequestMethod.GET)
    public Message searchAccountingStatementInfo(@RequestParam(value = "id") Long id) {
        Message msg = new Message();
        AuditCompanyEntity en = auditCompanyService.searchAuditCompanyIn(id);
        Long pid = Long.valueOf(en.getEnterpriseId());

        List<CompanyFileEntity> list = auditCompanyService.searchAccountingStatementInfo(pid);
        List<CompanyFileEntity> companyFileEntities = new ArrayList<>();
        List yearTime = (List) auditCompanyService.searchAccountingStatementTime().getMessage();
        String year1 = yearTime.get(0) + "";
        String year2 = yearTime.get(1) + "";
        String year3 = yearTime.get(2) + "";
        for (CompanyFileEntity entity : list) {
            if (entity.getCategory().equals("assetsTable") || entity.getCategory().equals("profitsTable") || entity.getCategory().equals("cashTable")) {
                if (entity.getYear().equals(year1) || entity.getYear().equals(year2) || entity.getYear().equals(year3)) {
                    companyFileEntities.add(entity);
                }
            }
        }
        msg.setMessage(companyFileEntities);
        msg.setCode(0);
        return msg;
    }

    @ApiOperation(value = "查询影像资料信息", notes = "查询影像资料信息")
    @ResponseBody
    @RequestMapping(value = "/searchImageDataInfo", method = RequestMethod.GET)
    public Message searchImageDataInfo(@RequestParam(value = "id") Long id) {
        Message msg = new Message();
        AuditCompanyEntity en = auditCompanyService.searchAuditCompanyIn(id);
        Long pid = Long.valueOf(en.getEnterpriseId());

        List<CompanyFileEntity> list = auditCompanyService.searchAccountingStatementInfo(pid);
        List yearTime = (List) auditCompanyService.searchAccountingStatementTime().getMessage();
        String year1 = yearTime.get(0) + "";
        String year2 = yearTime.get(1) + "";
        String year3 = yearTime.get(2) + "";
        Map result = new HashMap();
        List fileEntities = new ArrayList<>();
        boolean flag = false;
        for(CompanyFileEntity entity : list){
            if(!(entity.getCategory().equals("assetsTable") || entity.getCategory().equals("profitsTable")  || entity.getCategory().equals("cashTable"))) {
                if(Strings.isNullOrEmpty(entity.getYear())){
                    fileEntities.add(entity);
                } else if (entity.getYear().equals(year1) || entity.getYear().equals(year2) || entity.getYear().equals(year3)) {
                    fileEntities.add(entity);
                }

                if(entity.getCategory().equals("agentFacade")){
                    flag = true;
                }
            }
        }
        result.put("fileEntities", fileEntities);
        result.put("flag", flag);
        msg.setMessage(result);
        return msg;
    }

    @ApiOperation(value = "查询企业尽调审核详情", notes = "查询企业尽调审核详情")
    @RequestMapping(value = "/searchAuditCompanyInfo", method = RequestMethod.GET)
    public Message searchAuditCompanyInfo(@RequestParam(value = "id") Long id) {
        return auditCompanyService.searchAuditCompanyInfo(id);
    }

    @ApiOperation(value = "提交已检查", notes = "提交已检查")
    @RequestMapping(value = "/submitExaminationStatus", method = RequestMethod.POST)
    public Message submitExaminationStatus(@RequestBody AuditExaminationForm examine) {
        return auditCompanyService.submitExaminationStatus(examine);
    }

    @ApiOperation(value = "提交企业信息已检查意见", notes = "提交企业信息已检查意见")
    @RequestMapping(value = "/submitExaminationInfo", method = RequestMethod.POST)
    public Message submitExaminationInfo(@RequestBody AuditExaminationInfoForm auditExaminationInfoForm) {
        return auditCompanyService.submitExaminationInfo(auditExaminationInfoForm);
    }

    @ApiOperation(value = "现场调查录入提交检查意见", notes = "现场调查录入提交检查意见")
    @RequestMapping(value = "/submitSurveyInfo", method = RequestMethod.POST)
    public Message submitSurveyInfo(@RequestBody AuditSurveyInfoForm auditSurveyInfoForm) {
        return auditCompanyService.submitSurveyInfo(auditSurveyInfoForm);
    }

    @ApiOperation(value = "外部网站查询检查意见", notes = "外部网站查询检查意见")
    @RequestMapping(value = "/submitWebsiteInfo", method = RequestMethod.POST)
    public Message submitWebsiteInfo(@RequestBody AuditWebsiteInfoForm auditWebsiteInfoForm) {
        return auditCompanyService.submitWebsiteInfo(auditWebsiteInfoForm);
    }

    @ApiOperation(value = "提交到授信审核", notes = "提交到授信审核")
    @RequestMapping(value = "/submitCredit", method = RequestMethod.POST)
    public Message submitCredit(@RequestBody AuditSubmitCreditForm auditSubmitCreditForm) {
        return auditCompanyService.submitCredit(auditSubmitCreditForm);
    }

    @ApiOperation(value = "查询尽调状态", notes = "查询尽调状态")
    @RequestMapping(value = "/searchSurveyStatus", method = RequestMethod.GET)
    public Message searchSurveyStatus(@RequestParam(value = "id") Long id) {
        return auditCompanyService.searchSurveyStatus(id);
    }

    @ApiOperation(value = "查询授信录入列表", notes = "查询授信录入列表")
    @RequestMapping(value = "/searchCreditEntry", method = RequestMethod.POST)
    public Message searchCreditEntry(@RequestBody AuditCompanyEntryForm auditCompanyEntryForm) {
        return auditCompanyService.searchCreditEntry(auditCompanyEntryForm);
    }


    @ApiOperation(value = "查询现场调查意见", notes = "查询现场调查意见")
    @RequestMapping(value = "/searchSurveyInfo", method = RequestMethod.GET)
    public Message searchSurveyInfo(@RequestParam(value = "id") String id) {
        return auditCompanyService.searchSurveyInfo(id);
    }

    @ApiOperation(value = "查询外部网站查询意见", notes = "查询外部网站查询意见")
    @RequestMapping(value = "/searchWebsiteInfo", method = RequestMethod.GET)
    public Message searchWebsiteInfo(@RequestParam(value = "id") String id) {
        return auditCompanyService.searchWebsiteInfo(id);
    }

    @ApiOperation(value = "查询输出尽调结果意见", notes = "查询输出尽调结果意见")
    @RequestMapping(value = "/searchCreditReview", method = RequestMethod.GET)
    public Message searchCreditReview(@RequestParam(value = "id") String id) {
        return auditCompanyService.searchCreditReview(id);
    }

    @ApiOperation(value = "提交授信审批", notes = "提交授信审批")
    @RequestMapping(value = "/submitCreditReview", method = RequestMethod.POST)
    public Message submitCreditReview(@RequestBody AuditCreditReviewForm auditCreditReviewForm) {
        return auditCompanyService.submitCreditReview(auditCreditReviewForm);
    }


    @ApiOperation(value = "查询授信信息", notes = "查询授信信息")
    @RequestMapping(value = "/searchCreditInfo", method = RequestMethod.GET)
    public Message searchCreditInfo(@RequestParam(value = "type") String type, @RequestParam(value = "pid") Long pid) {
        Message msg = new Message();
        HashMap result = new HashMap();
        if (type.equals("1")) {
            msg.setMessage("间接授信目前不存在");
            msg.setCode(0);
            return msg;
        }

        if (!(type.equals("1")) && !(type.equals("0"))) {
            msg.setMessage("传入值有误");
            msg.setCode(-1);
            return msg;
        }
        if (type.equals("0")) {
            AuditCreditInfoEntity entity1 = auditCompanyService.searchCreditInfo(pid);
            AuditCompanyEntity en = auditCompanyService.searchAuditCompanyIn(pid);
            String enterpriseId = en.getEnterpriseId();
            type="creditAgreement";
            Message<List<AuditFileEntity>> listMessage = fcAuditFileService.searchAllAuditFileByOwnerIdAndCategory(enterpriseId + "", type);
            try {
                AuditFileEntity entity = listMessage.getMessage().get(0);
                Long id = entity.getPid();
                result.put("fileId", id);
                AuditCompanyEntity companyEntity = guaranteeManagerService.searchAuditCompanyId(enterpriseId);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                if(null !=companyEntity.getEntryTime()){
                    Date entryDate = companyEntity.getEntryTime();
                    String entryTime = sdf.format(entryDate);
                    result.put("entryTime",entryTime);
                }

                if(null != companyEntity.getEntryTime()){
                    Date auditDate = companyEntity.getAuditTime();
                    String auditTime =  sdf.format(auditDate);
                    result.put("auditTime",auditTime);
                }
            }catch (Exception e){
                e.printStackTrace();
            }

            result.put("entity1", entity1);
            msg.setMessage(result);
            msg.setCode(0);
        }
        return msg;
    }


    @ApiOperation(value = "查詢授信審批列表", notes = "查詢授信審批列表")
    @RequestMapping(value = "/searchCreditList", method = RequestMethod.POST)
    public Message searchCreditList(@RequestBody AuditCreditListForm auditCreditListForm) {
        return auditCompanyService.searchCreditList(auditCreditListForm);
    }

    @ApiOperation(value = "授信", notes = "授信")
    @RequestMapping(value = "/submitCreditReviewResult", method = RequestMethod.POST)
    public Message submitCreditReviewResult(@RequestBody AuditCreditReviewForm auditCreditReviewForm) {
        return auditCompanyService.submitCreditReviewResult(auditCreditReviewForm);
    }


    @ApiOperation(value = "修改尽调意见", notes = "修改尽调意见")
    @RequestMapping(value = "/updateAllResult", method = RequestMethod.POST)
    public Message updateAllResult(@RequestBody AuditExaminationForm form) {
        return auditCompanyService.updateAllResult(form);
    }
}
