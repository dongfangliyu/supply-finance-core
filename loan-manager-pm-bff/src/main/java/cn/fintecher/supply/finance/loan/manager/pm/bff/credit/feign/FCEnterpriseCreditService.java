package cn.fintecher.supply.finance.loan.manager.pm.bff.credit.feign;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.CompanyCreditStepForm;
import cn.fintecher.supply.finance.loan.manager.common.form.SearchCompanyCreditForm;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * @Description:
 * @Author WuTianJuan
 * @Date Created in 16:25 2018/6/19
 */
@FeignClient(name = "loan-manager-service")
public interface FCEnterpriseCreditService {

    @ResponseBody
    @RequestMapping(value ="/credit/enterprise/searchCompanyCreditStatus", method = RequestMethod.POST)
    Message searchCompanyCreditStatus(@RequestBody SearchCompanyCreditForm searchCompanyCreditForm);

    @ResponseBody
    @RequestMapping(value ="/credit/enterprise/startCompanyCredit", method = RequestMethod.GET)
    Message startCompanyCredit(@RequestParam(value = "pid") Long pid);

    @ResponseBody
    @RequestMapping(value ="/credit/enterprise/searchAccountingStatementTime", method = RequestMethod.GET)
    Message searchAccountingStatementTime();

    @ResponseBody
    @RequestMapping(value ="/credit/enterprise/applyCompanyCredit", method = RequestMethod.GET)
   // Message applyCompanyCredit();
    Message applyCompanyCredit(@RequestParam(value = "pid") Long pid);

    @ResponseBody
    @RequestMapping(value ="/credit/enterprise/searchCompanyCreditResult", method = RequestMethod.GET)
    Message searchCompanyCreditResult(@RequestParam(value = "pid") Long pid);

    @ResponseBody
    @RequestMapping(value ="/credit/enterprise/searchSupplierList", method = RequestMethod.GET)
    Message searchSupplierList(@RequestParam(value = "pid") String pid);
}
