package cn.fintecher.supply.finance.loan.manager.service.audit.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditRemindForm;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditTaskRemindForm;
import cn.fintecher.supply.finance.loan.manager.service.audit.service.AuditRemindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author gonghebin
 * @date 2018/7/23 0023上午 10:36
 */
@RestController
@RequestMapping("/audit/remindService")
public class AuditRemindController {

    @Autowired
    private AuditRemindService auditRemindService;
    
    @ResponseBody
    @RequestMapping(value = "/searchRemindList", method = RequestMethod.POST)
    public Message searchRemindList(@RequestBody AuditRemindForm auditRemindForm){
        return auditRemindService.searchRemindList(auditRemindForm);
    }

    @ResponseBody
    @RequestMapping(value = "/searchRemindDetail", method = RequestMethod.GET)
    public Message searchRemindDetail(@RequestParam(value = "pid") String pid){
        return auditRemindService.searchRemindDetail(pid);
    }

    @ResponseBody
    @RequestMapping(value = "/searchRemind", method = RequestMethod.POST)
    public Message searchRemind(@RequestBody AuditTaskRemindForm auditTaskRemindForm){
        return auditRemindService.searchRemind(auditTaskRemindForm);
    }

    @ResponseBody
    @RequestMapping(value = "/submitRemind", method = RequestMethod.POST)
    public Message submitRemind(@RequestBody AuditTaskRemindForm auditTaskRemindForm){
        return auditRemindService.submitRemind(auditTaskRemindForm);
    }
    
    @ResponseBody
    @RequestMapping(value = "/deleteRemind", method = RequestMethod.GET)
    public Message deleteRemind(@RequestParam(value = "pid") String pid){
        return auditRemindService.deleteRemind(pid);
    }
    
    
}
