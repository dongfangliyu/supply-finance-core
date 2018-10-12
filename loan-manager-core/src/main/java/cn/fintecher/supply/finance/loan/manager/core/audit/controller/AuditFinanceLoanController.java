package cn.fintecher.supply.finance.loan.manager.core.audit.controller;

import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditOrderInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.AuditSigningListForm;
import cn.fintecher.supply.finance.loan.manager.core.audit.service.AuditFinanceLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author WuTianJuan
 * @Date Created in 18:41 2018/7/23
 */
@RestController
@RequestMapping("/audit/auditOrder")
public class AuditFinanceLoanController{
    @Autowired
    private AuditFinanceLoanService auditFinanceLoanService;

    @ResponseBody
    @RequestMapping(value = "/searchSigningList", method = RequestMethod.POST)
    public List<AuditOrderInfoEntity> searchSigningList(@RequestBody AuditSigningListForm auditSigningListForm){
        return auditFinanceLoanService.searchSigningList(auditSigningListForm);
    }

    @ResponseBody
    @RequestMapping(value = "/searchSignListCount", method = RequestMethod.POST)
    public int searchSignListCount(@RequestBody  AuditSigningListForm auditSigningListForm){
        return auditFinanceLoanService.searchSignListCount(auditSigningListForm);
    }
}
