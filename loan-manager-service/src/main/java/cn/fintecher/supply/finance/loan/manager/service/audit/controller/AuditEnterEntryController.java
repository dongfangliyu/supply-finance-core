package cn.fintecher.supply.finance.loan.manager.service.audit.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditEntryForm;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditRemindForm;
import cn.fintecher.supply.finance.loan.manager.service.audit.service.AuditEnterEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author gonghebin
 * @date 2018/7/24 0024下午 4:02
 */
@RestController
@RequestMapping("/audit/entryEnterService")
public class AuditEnterEntryController {
    
    @Autowired
    private AuditEnterEntryService auditEnterEntryService;


    @ResponseBody
    @RequestMapping(value = "/searchEntryList", method = RequestMethod.POST)
    public Message searchEntryList(@RequestBody AuditRemindForm auditRemindForm){
        return auditEnterEntryService.searchEntryList(auditRemindForm);
    }

    @ResponseBody
    @RequestMapping(value = "/searchEntryDetail", method = RequestMethod.GET)
    public Message searchEntryDetail(@RequestParam(value = "pid") String pid){
        return auditEnterEntryService.searchEntryDetail(pid); 
    }

    @ResponseBody
    @RequestMapping(value = "/submitEntry", method = RequestMethod.POST)
    public Message submitEntry(@RequestBody AuditEntryForm auditEntryForm){
        return auditEnterEntryService.submitEntry(auditEntryForm);
    }
    
}
