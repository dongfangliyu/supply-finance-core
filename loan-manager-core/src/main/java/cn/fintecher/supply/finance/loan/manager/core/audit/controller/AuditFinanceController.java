package cn.fintecher.supply.finance.loan.manager.core.audit.controller;

import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditOrderInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.AuditFinanceForm;
import cn.fintecher.supply.finance.loan.manager.core.audit.service.AuditFinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/7/18 0018下午 7:41
 */
@RestController
@RequestMapping("/audit/financeCore")
public class AuditFinanceController {

    @Autowired
    private AuditFinanceService auditFinanceService;


    @ResponseBody
    @RequestMapping(value ="/searchFinanceListCount", method = RequestMethod.POST)
    public int searchFinanceListCount(@RequestBody AuditFinanceForm auditFinanceForm){
        return auditFinanceService.searchFinanceListCount(auditFinanceForm);
    }

    @ResponseBody
    @RequestMapping(value ="/searchFinanceList", method = RequestMethod.POST)
    public List<AuditOrderInfoEntity> searchFinanceList(@RequestBody AuditFinanceForm auditFinanceForm){
        return auditFinanceService.searchFinanceList(auditFinanceForm);
    }


    @ResponseBody
    @RequestMapping(value ="/searchLoanListCount", method = RequestMethod.POST)
    public int searchLoanListCount(@RequestBody AuditFinanceForm auditFinanceForm){
        return auditFinanceService.searchLoanListCount(auditFinanceForm);
    }

    @ResponseBody
    @RequestMapping(value ="/searchLoanList", method = RequestMethod.POST)
    public List<AuditOrderInfoEntity> searchLoanList(@RequestBody AuditFinanceForm auditFinanceForm){
        return auditFinanceService.searchLoanList(auditFinanceForm);
    }
    
}
