package cn.fintecher.supply.finance.loan.manager.service.credit.controller;


import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.SearchCompanyCreditForm;
import cn.fintecher.supply.finance.loan.manager.service.credit.service.impl.EnterpriseCreditServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * EnterpriseCreditController
 *
 * @author wutianjuan
 * @date 2018-06-15 11:25:23
 */
@RestController
@RequestMapping("/credit/enterprise")
public class EnterpriseCreditController {
    @Autowired
    private EnterpriseCreditServiceImpl enterpriseCreditService;

    /**
     * 查询企业授信状态
     */
    @ResponseBody
    @RequestMapping(value = "/searchCompanyCreditStatus", method = RequestMethod.POST)
    public Message searchCompanyCreditStatus(@RequestBody SearchCompanyCreditForm searchCompanyCreditForm) {
        return enterpriseCreditService.searchCompanyCreditStatus(searchCompanyCreditForm);
    }

    /**
     * 开始授信S
     */
    @ResponseBody
    @RequestMapping(value = "/startCompanyCredit", method = RequestMethod.GET)
    public Message startCompanyCredit(@RequestParam(value = "pid") Long pid) {
        String code = "2";
        return enterpriseCreditService.startCompanyCredit(pid,code);
    }

    /**
     * 查询财务报表期间
     */
    @ResponseBody
    @RequestMapping(value = "/searchAccountingStatementTime", method = RequestMethod.GET)
    public Message searchAccountingStatementTime() {
        return enterpriseCreditService.searchAccountingStatementTime();
    }

    /**
     * 申请授信
     */
    @ResponseBody
    @RequestMapping(value = "/applyCompanyCredit", method = RequestMethod.GET)
    public Message applyCompanyCredit(@RequestParam(value = "pid") Long pid) {
        String code = "2";
        return enterpriseCreditService.startCompanyCredit(pid,code);
    }

    /**
     * 查询授信结果
     */
    @ResponseBody
    @RequestMapping(value = "/searchCompanyCreditResult", method = RequestMethod.GET)
    public Message searchCompanyCreditResult(@RequestParam(value = "pid") Long pid) {
        return enterpriseCreditService.searchCompanyCreditResult(pid);
    }

    @ResponseBody
    @RequestMapping(value = "/searchSupplierList", method = RequestMethod.GET)
    public Message searchSupplierList(@RequestParam(value = "pid") String pid){
        return enterpriseCreditService.searchSupplierList(pid);
    }
}

