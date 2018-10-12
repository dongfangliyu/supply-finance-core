package cn.fintecher.supply.finance.loan.manager.service.audit.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.AuditFinanceForm;
import cn.fintecher.supply.finance.loan.manager.service.audit.service.AuditLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author gonghebin
 * @date 2018/7/19 0019上午 10:55
 */
@RestController
@RequestMapping("/audit/loanService")
public class AuditLoanController {
    
    @Autowired
    private AuditLoanService auditLoanService;


    @ResponseBody
    @RequestMapping(value = "/searchLoanList", method = RequestMethod.POST)
    public Message searchLoanList(@RequestBody AuditFinanceForm auditFinanceForm){
        return auditLoanService.searchLoanList(auditFinanceForm);
    }

    @ResponseBody
    @RequestMapping(value = "/searchLoanDetail", method = RequestMethod.GET)
    public Message searchLoanDetail(String pid){
        return auditLoanService.searchLoanDetail(pid);
    }

    @ResponseBody
    @RequestMapping(value = "/submitLoanPass", method = RequestMethod.GET)
    public Message submitLoanPass(@RequestParam(value = "pid") String pid,@RequestParam(value = "loanTime") String loanTime, @RequestParam(value = "userName") String userName){
        return auditLoanService.submitLoanPass(pid,loanTime,userName);
    }

    @ResponseBody
    @RequestMapping(value = "/submitLoanFail", method = RequestMethod.GET)
    public Message submitLoanFail(@RequestParam(value = "pid") String pid,@RequestParam(value = "userName") String userName){
        return auditLoanService.submitLoanFail(pid,userName);
    }
    
}
