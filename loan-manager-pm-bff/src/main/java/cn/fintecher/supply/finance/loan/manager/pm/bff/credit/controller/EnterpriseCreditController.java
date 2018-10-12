package cn.fintecher.supply.finance.loan.manager.pm.bff.credit.controller;


import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.SearchCompanyCreditForm;
import cn.fintecher.supply.finance.loan.manager.pm.bff.Base.controller.BaseController;
import cn.fintecher.supply.finance.loan.manager.pm.bff.credit.service.EnterpriseCreditService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


/**
 * EnterpriseCreditController
 * @author wutianjuan
 * @date 2018-06-15 11:25:23
 */
@RestController
@RequestMapping("/company/enterprise")
@Api(tags ="企业授信相关接口")
public class EnterpriseCreditController extends BaseController {
    @Autowired
    private EnterpriseCreditService enterpriseCreditService;

    /**
     * 查询企业授信状态
     */
    @ApiOperation(value = "查询企业授信状态", notes = "查询企业授信状态")
    @ResponseBody
    @RequestMapping(value = "/searchCompanyCreditStatus", method = RequestMethod.POST)
    public Message searchCompanyCreditStatus(Principal principal) {
        SearchCompanyCreditForm searchCompanyCreditForm = new SearchCompanyCreditForm();
        String userName = principal.getName();
        Long pid = getEnterpriseId(principal);
        searchCompanyCreditForm.setName(userName);
        searchCompanyCreditForm.setPid(pid);
        return enterpriseCreditService.searchCompanyCreditStatus(searchCompanyCreditForm);
    }

    /**
     *
     *
     * 开始申请授信
     */
    @ApiOperation(value = "开始申请授信", notes = "开始申请授信")
    @ResponseBody
    @RequestMapping(value = "/startCompanyCredit", method = RequestMethod.GET)
    public Message startCompanyCredit(Principal principal) {
        Long pid = getEnterpriseId(principal);
        return enterpriseCreditService.startCompanyCredit(pid);
    }

    /**
     * 查询财务报表期间
     */
    @ApiOperation(value = "查询财务报表期间", notes = "查询财务报表期间")
    @ResponseBody
    @RequestMapping(value = "/searchAccountingStatementTime", method = RequestMethod.GET)
    public Message searchAccountingStatementTime() {
        return enterpriseCreditService.searchAccountingStatementTime();
    }

    /**
     * 申请授信
     */
    @ApiOperation(value = "申请授信", notes = "申请授信")
    @ResponseBody
    @RequestMapping(value = "/applyCompanyCredit", method = RequestMethod.GET)
    public Message ApplyCompanyCredit(Principal principal) {
        Long pid = getEnterpriseId(principal);
        return enterpriseCreditService.applyCompanyCredit(pid);
    }

    /**
     * 查询授信结果
     */
    @ApiOperation(value = "查询授信结果", notes = "查询授信结果")
    @ResponseBody
    @RequestMapping(value = "/searchCompanyCreditResult", method = RequestMethod.GET)
    public Message searchCompanyCreditResult(Principal principal) {
        Long pid = getEnterpriseId(principal);
        return enterpriseCreditService.searchCompanyCreditResult(pid);
    }

    /**
     * 查询供应商列表
     */
    @ApiOperation(value = "查询供应商列表", notes = "查询供应商列表")
    @ResponseBody
    @RequestMapping(value = "/searchSupplierList", method = RequestMethod.GET)
    public Message searchSupplierList(Principal principal){
        Long pid = getEnterpriseId(principal);
        return enterpriseCreditService.searchSupplierList(pid.toString());
    }

}