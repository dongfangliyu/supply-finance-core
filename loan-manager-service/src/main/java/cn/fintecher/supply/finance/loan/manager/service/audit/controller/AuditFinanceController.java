package cn.fintecher.supply.finance.loan.manager.service.audit.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.AuditFinanceForm;
import cn.fintecher.supply.finance.loan.manager.service.audit.service.AuditFinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author gonghebin
 * @date 2018/7/18 0018下午 3:28
 */
@RestController
@RequestMapping("/audit/financeService")
public class AuditFinanceController {

    @Autowired
    private AuditFinanceService auditFinanceService;

    @ResponseBody
    @RequestMapping(value = "/searchFinanceList", method = RequestMethod.POST)
    public Message searchFinanceList(@RequestBody AuditFinanceForm auditFinanceForm){
        return auditFinanceService.searchFinanceList(auditFinanceForm);
    }

    @ResponseBody
    @RequestMapping(value = "/searchFinanceDetail", method = RequestMethod.GET)
    public Message searchFinanceDetail(String pid){
        return auditFinanceService.searchFinanceDetail(pid);
    }

    @ResponseBody
    @RequestMapping(value = "/submitFinancePass", method = RequestMethod.GET)
    public Message submitFinancePass(@RequestParam(value = "pid") String pid,@RequestParam(value = "userName") String userName){
        return auditFinanceService.submitFinancePass(pid,userName);
    }

    @ResponseBody
    @RequestMapping(value = "/submitFinanceFail", method = RequestMethod.GET)
    public Message submitFinanceFail(@RequestParam(value = "pid") String pid,@RequestParam(value = "userName") String userName){
        return auditFinanceService.submitFinanceFail(pid,userName);
    }

}
