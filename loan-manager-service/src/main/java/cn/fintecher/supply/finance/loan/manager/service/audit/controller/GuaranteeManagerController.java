package cn.fintecher.supply.finance.loan.manager.service.audit.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.form.guarantee.AuditFrontGuaranteeListForm;
import cn.fintecher.supply.finance.loan.manager.common.form.guarantee.AuditGuaranteeListForm;
import cn.fintecher.supply.finance.loan.manager.common.form.guarantee.AuditManagerListForm;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditCompanyEntity;
import cn.fintecher.supply.finance.loan.manager.service.audit.service.GuaranteeManagerService;
import cn.fintecher.supply.finance.loan.manager.service.common.controller.BaseCompanyUserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author WuTianJuan
 * @Date Created in 20:20 2018/7/17
 */
@RestController
@RequestMapping("/audit/auditGuarantee")
public class GuaranteeManagerController extends BaseCompanyUserController {
    @Autowired
    private GuaranteeManagerService guaranteeManagerService;


    @ResponseBody
    @RequestMapping(value = "/searchGuaranteeList", method = RequestMethod.POST)
    public Message searchGuaranteeList(@RequestBody AuditGuaranteeListForm auditGuaranteeListForm) {
        return guaranteeManagerService.searchGuaranteeList(auditGuaranteeListForm);
    }

    @ResponseBody
    @RequestMapping(value = "/submitResult", method = RequestMethod.POST)
    public Message submitResult(@RequestBody AuditManagerListForm auditManagerListForm) {
        return guaranteeManagerService.submitResult(auditManagerListForm);
    }

    @ResponseBody
    @RequestMapping(value = "/searchFrontGuaranteeList", method = RequestMethod.POST)
    public Message searchFrontGuaranteeList(@RequestBody AuditFrontGuaranteeListForm auditFrontGuaranteeListForm) {
         try {
            auditFrontGuaranteeListForm.setCurrentCompanyId(getCompanyUser(auditFrontGuaranteeListForm.getCurrentUserName()).getEnterpriseId());
            return guaranteeManagerService.searchFrontGuaranteeList(auditFrontGuaranteeListForm);
       } catch (Exception e) {
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR, "audit_bff", e.getMessage());
       }
    }


    @ResponseBody
    @RequestMapping(value = "/searchFrontDetail", method = RequestMethod.GET)
    public Message searchFrontDetail(@RequestParam(value="pid") String pid){
        return guaranteeManagerService.searchFrontDetail(pid);
    }

    @ResponseBody
    @RequestMapping(value = "/submitFrontResult", method = RequestMethod.POST)
    public Message submitFrontResult(@RequestBody AuditManagerListForm auditManagerListForm){
        return guaranteeManagerService.submitFrontResult(auditManagerListForm);
    }

    @ResponseBody
    @RequestMapping(value = "/searchAuditCompanyInfo", method = RequestMethod.GET)
    public Message searchAuditCompanyInfo(@RequestParam(value = "id") String id){
        return guaranteeManagerService.searchAuditCompanyInfo(id);
    }

    @ResponseBody
    @RequestMapping(value = "/searchAuditCompanyId", method = RequestMethod.GET)
    public AuditCompanyEntity searchAuditCompanyId(@RequestParam(value = "id") String id){
        return guaranteeManagerService.searchAuditCompanyId(id);
    }

    @ResponseBody
    @RequestMapping(value = "/searchDetail", method = RequestMethod.GET)
    public  Message searchDetail(@RequestParam(value = "pid") Long pid){
        return guaranteeManagerService.searchDetail(pid);
    }
}
