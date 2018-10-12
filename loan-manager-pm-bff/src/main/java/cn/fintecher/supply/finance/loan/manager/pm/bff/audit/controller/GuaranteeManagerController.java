package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.form.guarantee.AuditCompanyInfoForm;
import cn.fintecher.supply.finance.loan.manager.common.form.guarantee.AuditFrontGuaranteeListForm;
import cn.fintecher.supply.finance.loan.manager.common.form.guarantee.AuditGuaranteeListForm;
import cn.fintecher.supply.finance.loan.manager.common.form.guarantee.AuditManagerListForm;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditCompanyEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.RegisterFileEntity;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.AuditCompanyService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.AuditFileService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.AuditFirstTrialService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.GuaranteeManagerService;
import com.google.common.base.Strings;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author WuTianJuan
 * @Date Created in 19:49 2018/7/17
 */
@RestController
@RequestMapping("/audit/auditGuarantee")
@Api(tags = "担保相关接口")
public class GuaranteeManagerController {
    @Autowired
    private GuaranteeManagerService guaranteeManagerService;

    @Autowired
    private AuditCompanyService auditCompanyService;

    @Autowired
    private AuditFileService auditFileService;

    @Autowired
    private AuditFirstTrialService auditFirstTrialService;


    @ApiOperation(value = "查询担保管理列表", notes = "查询担保管理列表")
    @RequestMapping(value = "/searchGuaranteeList", method = RequestMethod.POST)
    public Message searchGuaranteeList(@RequestBody AuditGuaranteeListForm auditGuaranteeListForm){
        return guaranteeManagerService.searchGuaranteeList(auditGuaranteeListForm);
    }

    @ApiOperation(value = "提交擔保結果", notes = "提交擔保結果")
    @RequestMapping(value = "/submitResult", method = RequestMethod.POST)
    public Message submitResult(@RequestBody AuditManagerListForm auditManagerListForm){
        return guaranteeManagerService.submitResult(auditManagerListForm);
    }

    @ApiOperation(value = "前台担保管理详情", notes = "前台担保管理详情")
    @RequestMapping(value = "/searchFrontDetail", method = RequestMethod.GET)
    public Message searchFrontDetail(@RequestParam(value="pid") String pid){
        return guaranteeManagerService.searchFrontDetail(pid);
    }


    @ApiOperation(value = "查询前台担保管理列表", notes = "查询前台担保管理列表")
    @RequestMapping(value = "/searchFrontGuaranteeList", method = RequestMethod.POST)
    public Message searchFrontGuaranteeList(@RequestBody AuditFrontGuaranteeListForm auditFrontGuaranteeListForm,Principal principal){
        try {
            auditFrontGuaranteeListForm.setCurrentUserName(principal.getName());
            return guaranteeManagerService.searchFrontGuaranteeList(auditFrontGuaranteeListForm);
        }catch (Exception e){
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"audit_bff",e.getMessage());
        }
    }


    @ApiOperation(value = "提交前台担保管理结果", notes = "提交前台担保管理结果")
    @RequestMapping(value = "/submitFrontResult", method = RequestMethod.POST)
    public Message submitFrontResult(@RequestBody AuditManagerListForm auditManagerListForm){
        return guaranteeManagerService.submitFrontResult(auditManagerListForm);
    }

    @ApiOperation(value = "担保管理详情", notes = "担保管理详情")
    @RequestMapping(value = "/searchDetail", method = RequestMethod.GET)
    public Message searchDetail(@RequestParam(value="pid") Long pid){
        String userName = "12434";
        return auditFirstTrialService.searchDetail(pid,userName);
    }

    @ApiOperation(value = "查询企业基本信息", notes = "查询企业基本信息")
    @RequestMapping(value = "/searchAuditCompanyInfo", method = RequestMethod.POST)
    public Message searchAuditCompanyInfo(@RequestBody  AuditCompanyInfoForm auditCompanyInfoForm){
        Message msg = new Message();
        if(auditCompanyInfoForm.getType().equals("0")){//核心企业
            return guaranteeManagerService.searchAuditCompanyInfo(auditCompanyInfoForm.getEnterpriseId());
        }
        if(auditCompanyInfoForm.getType().equals("1")){//供应商
            return guaranteeManagerService.searchAuditCompanyInfo(auditCompanyInfoForm.getSupplyId());
        }
        if(!(auditCompanyInfoForm.getType().equals("0")) && !(auditCompanyInfoForm.getType().equals("1"))){
            msg.setCode(-1);
            msg.setMessage("输入参数有误");
        }
        return msg;
    }


    @ApiOperation(value = "查询财务报表信息", notes = "查询财务报表信息")
    @ResponseBody
    @RequestMapping(value = "/searchAccountingStatementInfo", method = RequestMethod.GET)
    public Message searchAccountingStatementInfo(@RequestParam(value="pid") String pid) {
        Message msg = new Message();
        List<CompanyFileEntity> companyFileEntities = new ArrayList<>();
        List<CompanyFileEntity> list = auditCompanyService.searchAccountingStatementInfo(Long.valueOf(pid));
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
    public Message searchImageDataInfo(@RequestParam(value="pid") String pid) {
        Message msg = new Message();
        List<CompanyFileEntity> list = auditCompanyService.searchAccountingStatementInfo(Long.valueOf(pid));
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

    @ApiOperation(value = "查询现场调查意见", notes = "查询现场调查意见")
    @RequestMapping(value = "/searchSurveyInfo", method = RequestMethod.GET)
    public Message searchSurveyInfo(@RequestParam(value = "pid") String pid) {
        //查询审核id
        AuditCompanyEntity entity = guaranteeManagerService.searchAuditCompanyId(pid);
        Long id = entity.getPid();
        return auditCompanyService.searchSurveyInfo(String.valueOf(id));
    }

    @ApiOperation(value = "查询外部网站查询意见", notes = "查询外部网站查询意见")
    @RequestMapping(value = "/searchWebsiteInfo", method = RequestMethod.GET)
    public Message searchWebsiteInfo(@RequestParam(value = "pid") String pid) {
        AuditCompanyEntity entity = guaranteeManagerService.searchAuditCompanyId(pid);
        Long id = entity.getPid();
        return auditCompanyService.searchWebsiteInfo(String.valueOf(id));
    }

    /**
     * 查看授信文档
     */
    @ApiOperation(value = "下载授信文档", notes = "下载授信文档",produces = MediaType.IMAGE_PNG_VALUE)
    @RequestMapping(value = "/donwloadCompanyCreditDoc", method = RequestMethod.GET)
    public ResponseEntity<byte[]> donwloadCompanyCreditDoc(@RequestParam(value="pid") Long pid, @RequestParam(value="type") String type, @RequestParam(value="year",required = false) String year) {
        return auditFileService.donwloadCompanyCreditDoc(pid,type,year);
    }


}
