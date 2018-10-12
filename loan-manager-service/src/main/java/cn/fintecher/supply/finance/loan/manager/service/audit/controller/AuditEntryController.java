package cn.fintecher.supply.finance.loan.manager.service.audit.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditEntryForm;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditRemindForm;
import cn.fintecher.supply.finance.loan.manager.service.audit.service.AuditEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author gonghebin
 * @date 2018/7/23 0023下午 5:45
 */
@RestController
@RequestMapping("/audit/entryService")
public class AuditEntryController {

    @Autowired
    private AuditEntryService auditEntryService;

    @ResponseBody
    @RequestMapping(value = "/searchEntryList", method = RequestMethod.POST)
    public Message searchEntryList(@RequestBody AuditRemindForm auditRemindForm){
        return auditEntryService.searchEntryList(auditRemindForm);
    }

    @ResponseBody
    @RequestMapping(value = "/searchEntryDetail", method = RequestMethod.GET)
    public Message searchEntryDetail(@RequestParam(value = "pid") String pid){
        return auditEntryService.searchEntryDetail(pid);
    }

    @ResponseBody
    @RequestMapping(value = "/searchEntry", method = RequestMethod.POST)
    public Message searchEntry(@RequestBody AuditEntryForm auditEntryForm){
        return auditEntryService.searchEntry(auditEntryForm);
    }

    @ResponseBody
    @RequestMapping(value = "/submitEntry", method = RequestMethod.POST)
    public Message submitEntry(@RequestBody AuditEntryForm auditEntryForm){
        return auditEntryService.submitEntry(auditEntryForm);
    }
    
}
