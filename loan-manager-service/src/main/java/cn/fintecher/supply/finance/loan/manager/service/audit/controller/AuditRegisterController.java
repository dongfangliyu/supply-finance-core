package cn.fintecher.supply.finance.loan.manager.service.audit.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.AuditRegisterForm;
import cn.fintecher.supply.finance.loan.manager.common.form.AuditRegisterResultForm;
import cn.fintecher.supply.finance.loan.manager.service.audit.service.AuditRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author gonghebin
 * @date 2018/7/4 0004下午 9:00
 */
@RestController
@RequestMapping("/audit/registerService")
public class AuditRegisterController {

    @Autowired
    private AuditRegisterService auditRegisterService;

    @ResponseBody
    @RequestMapping(value = "/searchListAuditRegister", method = RequestMethod.POST)
    public Message searchListAuditRegister(@RequestBody AuditRegisterForm auditRegisterForm){
       return auditRegisterService.searchListAuditRegister(auditRegisterForm);
    }

    @ResponseBody
    @RequestMapping(value = "/searchAuditRegisterCompanyInfo", method = RequestMethod.GET)
    public Message searchAuditRegisterCompanyInfo(@RequestParam(value = "pid") String pid){
        return auditRegisterService.searchAuditRegisterCompanyInfo(pid);
    }

    @ResponseBody
    @RequestMapping(value = "/submitAuditRegisterResult", method = RequestMethod.POST)
    public Message submitAuditRegisterResult(@RequestBody AuditRegisterResultForm auditRegisterResultForm){
        return auditRegisterService.submitAuditRegisterResult(auditRegisterResultForm);
    }

    @ResponseBody
    @RequestMapping(value = "/searchCompanyInfo", method = RequestMethod.GET)
    public Message searchCompanyInfo(@RequestParam(value = "pid") String pid){
        return auditRegisterService.searchCompanyInfo(pid);
    }

    @ResponseBody
    @RequestMapping(value = "/submitSendLink", method = RequestMethod.GET)
    public Message submitSendLink(@RequestParam(value = "pid") String pid){
        return auditRegisterService.submitSendLink(pid);
    }

    @ResponseBody
    @RequestMapping(value = "/searchAuditCreditStatus", method = RequestMethod.GET)
    public Message searchAuditCreditStatus(){
        return auditRegisterService.searchAuditCreditStatus();
    }

}
